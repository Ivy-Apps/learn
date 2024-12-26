package ui.screen.kpi

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
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

class KpiScreen : Screen() {
  override fun toRoute(): Route = KpiRouter.toRoute(this)

  val viewModel by lazy { Di.get<KpiViewModel>() }

  override fun Di.Scope.onDi() {
    autoWire(::KpiViewModel)
  }

  @Composable
  override fun Content() {
    val viewState = viewModel.viewState()
    KpiScreenContent(
      viewState = viewState,
    )
  }
}