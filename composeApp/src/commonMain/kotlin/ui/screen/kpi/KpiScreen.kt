package ui.screen.kpi

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.kpi.composable.KpiScreenContent

object KpiRouter : Router<KpiScreen> {
  const val PATH = "kpi"

  override fun fromRoute(route: Route): Option<KpiScreen> = option {
    ensure(route.path == PATH)
    KpiScreen()
  }

  override fun toRoute(screen: KpiScreen): Route {
    return Route(path = PATH)
  }
}

class KpiScreen : Screen<KpiViewState, KpiViewEvent>() {
  override val name = "kpi"
  override fun toRoute(): Route = KpiRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<KpiViewState, KpiViewEvent> {
    return Di.get<KpiViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::KpiViewModel)
  }

  @Composable
  override fun Content(viewState: KpiViewState, onEvent: (KpiViewEvent) -> Unit) {
    KpiScreenContent(
      viewState = viewState,
    )
  }
}