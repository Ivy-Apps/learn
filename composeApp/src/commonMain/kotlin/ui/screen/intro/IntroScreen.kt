package ui.screen.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.intro.composable.IntroContent

object IntroRouter : Router<IntroScreen> {
  const val PATH = "intro"
  const val SHOW_LEARN_MORE = "show_learn_more"

  override fun fromRoute(route: Route): Option<IntroScreen> = option {
    ensure(route.path == PATH)
    IntroScreen(
      showLearnMore = route.getBoolean(SHOW_LEARN_MORE).getOrElse { true }
    )
  }

  override fun toRoute(screen: IntroScreen): Route {
    return Route(
      path = PATH,
      params = mapOf(
        SHOW_LEARN_MORE to screen.showLearnMore.toString(),
      )
    )
  }
}

class IntroScreen(
  val showLearnMore: Boolean,
) : Screen<IntroViewState, IntroViewEvent>() {
  override val name = "intro"
  override fun toRoute(): Route = IntroRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<IntroViewState, IntroViewEvent> {
    return Di.get<IntroViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    register {
      Args(
        showLearnMore = showLearnMore,
      )
    }
    autoWire(::IntroViewModel)
  }

  @Composable
  override fun Content(viewState: IntroViewState, onEvent: (IntroViewEvent) -> Unit) {
    IntroContent(
      viewState = viewState,
      onEvent = onEvent,
    )
  }

  @Immutable
  data class Args(
    val showLearnMore: Boolean,
  )
}