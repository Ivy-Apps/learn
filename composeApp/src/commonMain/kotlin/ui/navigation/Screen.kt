package ui.navigation

import androidx.compose.runtime.Composable
import ivy.di.Di
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class Screen {

    private lateinit var job: CompletableJob
    protected lateinit var screenScope: CoroutineScope

    protected abstract fun onDi(): Di.ScreenScope.() -> Unit

    fun initialize() {
        job = SupervisorJob()
        screenScope = CoroutineScope(Dispatchers.Main + job)
        onDi().invoke(Di.ScreenScope)
    }

    fun destroy() {
        job.cancel()
        Di.clearInstances(Di.ScreenScope)
    }


    @Composable
    abstract fun Content()
}