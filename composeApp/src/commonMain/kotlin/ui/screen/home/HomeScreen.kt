package ui.screen.home

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ui.navigation.Screen
import ui.screen.home.composable.HomeContent
import ui.screen.home.mapper.HomeViewStateMapper

class HomeScreen : Screen() {
    override val path: String = "home"

    override fun onDi(): Di.ScreenScope.() -> Unit = {
        register { HomeViewStateMapper() }
        register { HomeViewModel(Di.get(), Di.get(), Di.get()) }
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