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
            SettingsViewEvent.OnPremiumClick -> handlePremiumClick()
            is SettingsViewEvent.OnSoundsEnabledChange -> handleSoundsEnabledChange(event)
            SettingsViewEvent.OnPrivacyClick -> handlePrivacyClick()
            SettingsViewEvent.OnDeleteAccountClick -> handleDeleteAccountClick()
            SettingsViewEvent.OnTermsOfUseClick -> handleTermsOfUseClick()
            SettingsViewEvent.OnPrivacyPolicyClick -> handlePrivacyPolicyClick()
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }

    private fun handlePremiumClick() {
        // TODO - handle event
    }

    private fun handleSoundsEnabledChange(event: SettingsViewEvent.OnSoundsEnabledChange) {
        soundsEnabled = event.enabled
    }

    private fun handlePrivacyClick() {
        // TODO - handle event
    }

    private fun handleDeleteAccountClick() {
        // TODO - handle event
    }

    private fun handleTermsOfUseClick() {
        // TODO - handle event
    }

    private fun handlePrivacyPolicyClick() {
        // TODO - handle event
    }
}