package ui.screen.settings

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.screen.home.HomeRouter
import ui.screen.settings.content.SettingsContent

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

class SettingsScreen : Screen() {
    override fun toRoute(): Route = SettingsRouter.toRoute(this)

    override fun Di.Scope.onDi() {
        autoWire(::SettingsViewModel)
    }

    private val viewModel: SettingsViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        SettingsContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}