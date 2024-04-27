package ui.screen.home

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ui.navigation.Screen
import ui.screen.home.composable.HomeContent

class HomeScreen : Screen() {
    override fun onDi(): Di.ScreenScope.() -> Unit = {
        register { HomeViewModel(Di.get()) }
    }

    private val viewModel: HomeViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        HomeContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}