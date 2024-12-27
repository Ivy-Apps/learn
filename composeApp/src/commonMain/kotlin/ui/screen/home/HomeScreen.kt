package ui.screen.home

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.home.composable.HomeContent
import ui.screen.home.mapper.HomeViewStateMapper

object HomeRouter : Router<HomeScreen> {
  const val PATH = "home"

  override fun fromRoute(route: Route): Option<HomeScreen> = option {
    ensure(route.path == PATH || route.path == "")
    HomeScreen()
  }

  override fun toRoute(screen: HomeScreen): Route {
    return Route(path = PATH)
  }
}

class HomeScreen : Screen<HomeViewState, HomeViewEvent>() {
  override val name: String = "home"
  override fun toRoute(): Route = HomeRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<HomeViewState, HomeViewEvent> {
    return Di.get<HomeViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::HomeViewStateMapper)
    autoWire(::HomeViewModel)
  }

  @Composable
  override fun Content(viewState: HomeViewState, onEvent: (HomeViewEvent) -> Unit) {
    HomeContent(
      viewState = viewState,
      onEvent = onEvent
    )
  }
}