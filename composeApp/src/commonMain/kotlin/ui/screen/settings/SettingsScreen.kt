package ui.screen.settings

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.settings.composable.SettingsContent

object SettingsRouter : Router<SettingsScreen> {
  const val PATH = "settings"

  override fun fromRoute(route: Route): Option<SettingsScreen> = option {
    ensure(route.path == PATH)
    SettingsScreen()
  }

  override fun toRoute(screen: SettingsScreen): Route {
    return Route(path = PATH)
  }
}

class SettingsScreen : Screen<SettingsViewState, SettingsViewEvent>() {
  override val name = "settings"
  override fun toRoute(): Route = SettingsRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<SettingsViewState, SettingsViewEvent> {
    return Di.get<SettingsViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::SettingsViewModel)
  }

  @Composable
  override fun Content(viewState: SettingsViewState, onEvent: (SettingsViewEvent) -> Unit) {
    SettingsContent(
      viewState = viewState,
      onEvent = onEvent
    )
  }
}