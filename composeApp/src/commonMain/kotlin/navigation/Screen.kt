package navigation

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.FeatureScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class Screen {

    private lateinit var job: CompletableJob
    protected lateinit var screenScope: CoroutineScope

    private var initialized = false

    abstract fun toRoute(): Route

    protected abstract fun onDi(): Di.Scope.() -> Unit

    fun initialize() {
        if (initialized) return

        job = SupervisorJob()
        screenScope = CoroutineScope(Dispatchers.Main + job)
        onDi().invoke(FeatureScope)
        initialized = true
    }

    fun destroy() {
        job.cancel()
        Di.clear(FeatureScope)
        initialized = false
    }


    @Composable
    abstract fun Content()
}