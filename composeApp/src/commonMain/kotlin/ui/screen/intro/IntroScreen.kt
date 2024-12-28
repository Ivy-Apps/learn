package ui.screen.intro

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.intro.composable.IntroContent

object IntroRouter : Router<IntroScreen> {
  const val PATH = "intro"

  override fun fromRoute(route: Route): Option<IntroScreen> = option {
    ensure(route.path == PATH)
    IntroScreen()
  }

  override fun toRoute(screen: IntroScreen): Route {
    return Route(PATH)
  }
}

class IntroScreen : Screen<IntroViewState, IntroViewEvent>() {
  override val name = "intro"
  override fun toRoute(): Route = IntroRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<IntroViewState, IntroViewEvent> {
    return Di.get<IntroViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::IntroViewModel)
  }

  @Composable
  override fun Content(viewState: IntroViewState, onEvent: (IntroViewEvent) -> Unit) {
    IntroContent(
      viewState = viewState,
      onEvent = onEvent,
    )
  }
}