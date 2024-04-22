package ivy.di

import kotlin.reflect.KClass


object Di {

    val singletons = mutableSetOf<String>()
    val instances = mutableMapOf<DependencyKey, Any>()
    val factories = mutableMapOf<DependencyKey, () -> Any>()

    fun appScope(block: DiScope.() -> Unit) {
        AppScope.block()
    }

    fun screenScope(block: DiScope.() -> Unit) {
        ScreenScope.block()
    }

    inline fun <reified T : Any> DiScope.register(noinline factory: () -> T) {
        factories[DependencyKey(this, T::class.diGraphKey())] = factory
    }

    inline fun <reified T : Any> DiScope.singleton(noinline factory: () -> T) {
        val className = T::class.diGraphKey()
        factories[DependencyKey(this, className)] = factory
        singletons.add(className)
    }

    inline fun <reified T : Any> get(): T {
        val classKey = T::class.diGraphKey()
        val (scope, factory) = factory(classKey)
        val depKey = DependencyKey(scope, classKey)
        return if (classKey in singletons) {
            if (depKey in instances) {
                // single instance already created
                instances[depKey] as T
            } else {
                // create a new instance for the singleton
                val instance = (factory() as T).also {
                    instances[depKey] = it
                }
                instance
            }
        } else {
            // create a new instance
            val instance = (factory() as T).also {
                instances[depKey] = it
            }
            instance
        }
    }

    inline fun factory(
        className: String
    ): Pair<DiScope, () -> Any> = scopedFactory(ScreenScope, className)
        ?: scopedFactory(AppScope, className)
        ?: throw DiError("No factory found for class $className")

    inline fun scopedFactory(
        scope: DiScope,
        classKey: String
    ): Pair<DiScope, () -> Any>? = factories[DependencyKey(scope, classKey)]?.let {
        scope to it
    }

    inline fun <reified T : Any> KClass<T>.diGraphKey(): String {
        // TODO: type.qualifiedName doesn't work because it's not supported on JS
        return simpleName!!
    }

    fun clear(scope: DiScope) {
        val keysForRemoval = factories.keys.filter {
            it.scope == scope
        }
        keysForRemoval.forEach {
            instances.remove(it)
            factories.remove(it)
        }
    }

    fun reset() {
        instances.clear()
        factories.clear()
        singletons.clear()
    }

    data class DependencyKey(
        val scope: DiScope,
        val className: String
    )

    sealed interface DiScope
    data object ScreenScope : DiScope
    data object AppScope : DiScope
}

class DiError(msg: String) : IllegalStateException(msg)