package ui.screen.intro

import androidx.compose.runtime.Composable
import ivy.di.Di
import ui.navigation.Navigation
import ui.navigation.Screen
import ui.screen.intro.composable.IntroContent

class IntroScreen : Screen() {
    private val navigation: Navigation = Di.get()

    override fun onDi(): Di.ScreenScope.() -> Unit = {}

    @Composable
    override fun Content() {
        IntroContent(
            viewState = IntroViewState(""),
            onEvent = {}
        )
    }
}