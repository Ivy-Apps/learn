package ivy.di

import kotlin.reflect.KClass


object Di {

    val singletons = mutableSetOf<KClass<*>>()
    val instances = mutableMapOf<DependencyKey, Any>()
    val factories = mutableMapOf<DependencyKey, () -> Any>()

    fun appScope(block: DiScope.() -> Unit) {
        AppScope.block()
    }

    fun screenScope(block: DiScope.() -> Unit) {
        ScreenScope.block()
    }

    inline fun <reified T : Any> DiScope.register(noinline factory: () -> T) {
        factories[DependencyKey(this, T::class)] = factory
    }

    inline fun <reified T : Any> DiScope.singleton(noinline factory: () -> T) {
        val classKey = T::class
        factories[DependencyKey(this, classKey)] = factory
        singletons.add(classKey)
    }

    inline fun <reified T : Any> get(): T {
        val classKey = T::class
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
        classKey: KClass<*>
    ): Pair<DiScope, () -> Any> = scopedFactory(ScreenScope, classKey)
        ?: scopedFactory(AppScope, classKey)
        ?: throw DiError("No factory found for class $classKey")

    inline fun scopedFactory(
        scope: DiScope,
        classKey: KClass<*>
    ): Pair<DiScope, () -> Any>? = factories[DependencyKey(scope, classKey)]?.let {
        scope to it
    }

    fun clear(scope: DiScope) {
        val keysForRemoval = instances.keys.filter {
            it.scope == scope
        }
        keysForRemoval.forEach {
            instances.remove(it)
        }
    }

    fun reset() {
        instances.clear()
        factories.clear()
        singletons.clear()
    }

    data class DependencyKey(
        val scope: DiScope,
        val klass: KClass<*>
    )

    sealed interface DiScope
    data object ScreenScope : DiScope
    data object AppScope : DiScope
}

class DiError(msg: String) : IllegalStateException(msg)