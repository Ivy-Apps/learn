package ui.screen.intro

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.autowire.autoWire
import ui.navigation.Screen
import ui.screen.intro.composable.IntroContent

class IntroScreen : Screen() {
    override val path: String = "intro"

    override fun onDi(): Di.Scope.() -> Unit = {
        autoWire(::IntroViewModel)
    }

    private val viewModel: IntroViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        IntroContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}