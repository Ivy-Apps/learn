package ui.screen.settings

import androidx.compose.runtime.*
import navigation.Navigation
import ui.ComposeViewModel

class SettingsViewModel(
    private val navigation: Navigation,
) : ComposeViewModel<SettingsViewState, SettingsViewEvent> {
    private var soundsEnabled by mutableStateOf(true)
    private var deleteDialogVisible by mutableStateOf(false)

    @Composable
    override fun viewState(): SettingsViewState {
        LaunchedEffect(Unit) {
            // TODO - fetch soundsEnabled from dataStore and update state
        }
        return SettingsViewState(
            soundsEnabled = getSoundsEnabled(),
            deleteDialogVisible = getDeleteDialogVisible()
        )
    }

    @Composable
    private fun getSoundsEnabled(): Boolean {
        return soundsEnabled
    }

    @Composable
    private fun getDeleteDialogVisible(): Boolean {
        return deleteDialogVisible
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
            SettingsViewEvent.OnCancelDeleteAccountClick -> handleConfirmDeleteAccountClick()
            SettingsViewEvent.OnConfirmDeleteAccountClick -> handleCancelDeleteAccountClick()
        }
    }

    private fun handleBackClick() {
        navigation.navigateBack()
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
        deleteDialogVisible = true
    }

    private fun handleTermsOfUseClick() {
        // TODO - handle event
    }

    private fun handlePrivacyPolicyClick() {
        // TODO - handle event
    }

    private fun handleConfirmDeleteAccountClick() {
        // TODO - handle event
    }

    private fun handleCancelDeleteAccountClick() {
        // TODO - handle event
    }
}