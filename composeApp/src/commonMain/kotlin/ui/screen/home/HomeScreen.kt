package ui.screen.home

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.autowire.autoWire
import ui.navigation.Screen
import ui.screen.home.composable.HomeContent
import ui.screen.home.mapper.HomeViewStateMapper

class HomeScreen : Screen() {
    override val path: String = "home"

    override fun onDi(): Di.Scope.() -> Unit = {
        autoWire(::HomeViewStateMapper)
        autoWire(::HomeViewModel)
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