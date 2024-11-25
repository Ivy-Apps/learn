package ui.screen.settings

import androidx.compose.runtime.*
import ui.ComposeViewModel
import ui.navigation.Navigation

class SettingsViewModel(
    private val navigation: Navigation,
) : ComposeViewModel<SettingsViewState, SettingsViewEvent> {

    @Composable
    override fun viewState(): SettingsViewState {
        return SettingsViewState()
    }

    override fun onEvent(event: SettingsViewEvent) {
        when (event) {
            SettingsViewEvent.OnBackClick -> handleBackClick()
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }
}