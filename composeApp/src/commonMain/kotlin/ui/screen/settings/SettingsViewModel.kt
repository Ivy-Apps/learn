package ui.screen.settings

import androidx.compose.runtime.*
import androidx.compose.ui.platform.UriHandler
import ivy.IvyUrls
import navigation.Navigation
import ui.ComposeViewModel

class SettingsViewModel(
    private val navigation: Navigation,
    private val uriHandler: UriHandler
) : ComposeViewModel<SettingsViewState, SettingsViewEvent> {
    private var soundEnabled by mutableStateOf(true)
    private var deleteDialogVisible by mutableStateOf(false)

    @Composable
    override fun viewState(): SettingsViewState {
        LaunchedEffect(Unit) {
            // TODO - fetch soundEnabled from dataStore and update state
        }
        return SettingsViewState(
            soundEnabled = getSoundEnabled(),
            deleteDialogVisible = getDeleteDialogVisible()
        )
    }

    @Composable
    private fun getSoundEnabled(): Boolean {
        return soundEnabled
    }

    @Composable
    private fun getDeleteDialogVisible(): Boolean {
        return deleteDialogVisible
    }

    override fun onEvent(event: SettingsViewEvent) {
        when (event) {
            SettingsViewEvent.OnBackClick -> handleBackClick()
            SettingsViewEvent.OnPremiumClick -> handlePremiumClick()
            is SettingsViewEvent.OnSoundEnabledChange -> handleSoundEnabledChange(event)
            SettingsViewEvent.OnPrivacyClick -> handlePrivacyClick()
            SettingsViewEvent.OnLogOutClick -> handleLogOutClick()
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

    private fun handleSoundEnabledChange(event: SettingsViewEvent.OnSoundEnabledChange) {
        soundEnabled = event.enabled
    }

    private fun handlePrivacyClick() {
        // TODO - handle event
    }

    private fun handleLogOutClick() {
        // TODO - handle event
    }

    private fun handleDeleteAccountClick() {
        deleteDialogVisible = true
    }

    private fun handleTermsOfUseClick() {
        uriHandler.openUri(IvyUrls.tos)
    }

    private fun handlePrivacyPolicyClick() {
        uriHandler.openUri(IvyUrls.privacy)
    }

    private fun handleConfirmDeleteAccountClick() {
        // TODO - handle event
    }

    private fun handleCancelDeleteAccountClick() {
        // TODO - handle event
    }
}