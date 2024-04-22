package ui.navigation

import androidx.compose.runtime.Composable
import ivy.di.Di

abstract class Screen {

    protected abstract fun onDi(): Di.ScreenScope.() -> Unit

    fun initialize() {
        onDi().invoke(Di.ScreenScope)
    }

    fun destroy() {
        Di.clearInstances(Di.ScreenScope)
    }

    @Composable
    abstract fun Content()
}