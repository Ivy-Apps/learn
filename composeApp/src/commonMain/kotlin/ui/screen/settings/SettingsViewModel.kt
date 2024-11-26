package ui.screen.settings

import androidx.compose.runtime.*
import ui.ComposeViewModel
import ui.navigation.Navigation

class SettingsViewModel(
    private val navigation: Navigation,
) : ComposeViewModel<SettingsViewState, SettingsViewEvent> {
    private var soundsEnabled by mutableStateOf(true)

    @Composable
    override fun viewState(): SettingsViewState {
        LaunchedEffect(Unit) {
            // TODO - fetch soundsEnabled from dataStore and update state
        }
        return SettingsViewState(
            soundsEnabled = getSoundsEnabled()
        )
    }

    @Composable
    private fun getSoundsEnabled(): Boolean {
        return soundsEnabled
    }

    override fun onEvent(event: SettingsViewEvent) {
        when (event) {
            SettingsViewEvent.OnBackClick -> handleBackClick()
            is SettingsViewEvent.OnSoundsEnabledChange -> handleSoundsEnabledChange(event)
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }

    private fun handleSoundsEnabledChange(event: SettingsViewEvent.OnSoundsEnabledChange) {
        soundsEnabled = event.enabled
    }
}