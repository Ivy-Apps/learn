package ui.navigation

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.FeatureScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class Screen {

    abstract val path: String

    private lateinit var job: CompletableJob
    protected lateinit var screenScope: CoroutineScope

    fun toRoute() = ""

    protected abstract fun onDi(): Di.Scope.() -> Unit

    fun initialize() {
        job = SupervisorJob()
        screenScope = CoroutineScope(Dispatchers.Main + job)
        onDi().invoke(FeatureScope)
    }

    fun destroy() {
        job.cancel()
        Di.clear(FeatureScope)
    }


    @Composable
    abstract fun Content()
}