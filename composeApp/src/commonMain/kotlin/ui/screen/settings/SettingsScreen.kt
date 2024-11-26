package ui.screen.settings

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.autowire.autoWire
import ui.navigation.Screen
import ui.screen.settings.content.SettingsContent

class SettingsScreen : Screen() {
    override val path: String = "settings"

    override fun onDi(): Di.Scope.() -> Unit = {
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