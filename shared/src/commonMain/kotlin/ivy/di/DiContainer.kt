package ivy.di


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
        factories[DependencyKey(this, T::class.qualifiedName!!)] = factory
    }

    inline fun <reified T : Any> DiScope.singleton(noinline factory: () -> T) {
        val className = T::class.qualifiedName!!
        factories[DependencyKey(this, className)] = factory
        singletons.add(className)
    }

    inline fun <reified T : Any> get(): T {
        val className = T::class.qualifiedName!!
        val (scope, factory) = factory(className)
        val depKey = DependencyKey(scope, className)
        return if (className in singletons) {
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
        className: String
    ): Pair<DiScope, () -> Any>? = factories[DependencyKey(scope, className)]?.let {
        scope to it
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