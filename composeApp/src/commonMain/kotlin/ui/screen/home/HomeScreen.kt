package ui.screen.home

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.screen.home.composable.HomeContent
import ui.screen.home.mapper.HomeViewStateMapper

object HomeRouter : Router<HomeScreen> {
    const val PATH = "home"

    override fun fromRoute(route: Route): Option<HomeScreen> = option {
        ensure(route.path == PATH)
        HomeScreen()
    }

    override fun toRoute(screen: HomeScreen): Route {
        return Route(path = PATH)
    }

}

class HomeScreen : Screen() {
    override fun toRoute(): Route = HomeRouter.toRoute(this)

    override fun Di.Scope.onDi() {
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