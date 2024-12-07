package ui.screen.settings

import androidx.compose.runtime.*
import androidx.compose.ui.platform.UriHandler
import data.SoundRepository
import domain.DeleteUserDataUseCase
import domain.SessionManager
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.IvyUrls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import navigation.Navigation
import ui.ComposeViewModel
import ui.Toaster
import ui.screen.intro.IntroScreen
import util.Logger

class SettingsViewModel(
    private val navigation: Navigation,
    private val uriHandler: UriHandler,
    private val sessionManager: SessionManager,
    private val scope: CoroutineScope,
    private val deleteUserDataUseCase: DeleteUserDataUseCase,
    private val logger: Logger,
    private val analytics: Analytics,
    private val toaster: Toaster,
    private val soundRepository: SoundRepository
) : ComposeViewModel<SettingsViewState, SettingsViewEvent> {
    private var soundEnabled by mutableStateOf(true)
    private var deleteDialog by mutableStateOf<DeleteDialogViewState?>(null)

    @Composable
    override fun viewState(): SettingsViewState {
        LaunchedEffect(Unit) {
            soundEnabled = soundRepository.getSoundEnabled()
            logEvent("view")
        }
        return SettingsViewState(
            soundEnabled = getSoundEnabled(),
            deleteDialog = getDeleteDialogState()
        )
    }

    @Composable
    private fun getSoundEnabled(): Boolean {
        return soundEnabled
    }

    @Composable
    private fun getDeleteDialogState(): DeleteDialogViewState? {
        return deleteDialog
    }

    override fun onEvent(event: SettingsViewEvent) {
        when (event) {
            SettingsViewEvent.OnBackClick -> handleBackClick()
            SettingsViewEvent.OnPremiumClick -> handlePremiumClick()
            is SettingsViewEvent.OnSoundEnabledChange -> handleSoundEnabledChange(event)
            SettingsViewEvent.OnPrivacyClick -> handlePrivacyClick()
            SettingsViewEvent.OnLogoutClick -> handleLogoutClick()
            SettingsViewEvent.OnDeleteAccountClick -> handleDeleteAccountClick()
            SettingsViewEvent.OnTermsOfUseClick -> handleTermsOfUseClick()
            SettingsViewEvent.OnPrivacyPolicyClick -> handlePrivacyPolicyClick()
            SettingsViewEvent.OnCancelDeleteAccountClick -> handleCancelDeleteAccountClick()
            SettingsViewEvent.OnConfirmDeleteAccountClick -> handleConfirmDeleteAccountClick()
        }
    }

    private fun handleBackClick() {
        navigation.navigateBack()
    }

    private fun handlePremiumClick() {
        logEvent("click_premium")
        // TODO - handle event
    }

    private fun handleSoundEnabledChange(event: SettingsViewEvent.OnSoundEnabledChange) {
        scope.launch {
            soundEnabled = event.enabled
            soundRepository.setSoundEnabled(event.enabled)
        }
    }

    private fun handlePrivacyClick() {
        scope.launch {
            if (analytics.enabled) {
                analytics.disable()
                toaster.showToast("Analytics disabled")
            } else {
                analytics.enable()
                toaster.showToast("Analytics enabled")
            }
        }

        // TODO - handle event
    }

    private fun handleLogoutClick() {
        logEvent("click_logout")
        scope.launch {
            sessionManager.logout()
            navigation.replaceWith(IntroScreen())
        }
    }

    private fun handleTermsOfUseClick() {
        logEvent("click_tos")
        uriHandler.openUri(IvyUrls.tos)
    }

    private fun handlePrivacyPolicyClick() {
        logEvent("click_privacy")
        uriHandler.openUri(IvyUrls.privacy)
    }

    private fun handleDeleteAccountClick() {
        logEvent("click_delete_account")
        deleteDialog = DeleteDialogViewState(ctaLoading = false)
    }

    private fun handleConfirmDeleteAccountClick() {
        scope.launch {
            logger.debug("VM: Initiating user data deletion...")
            deleteDialog = DeleteDialogViewState(ctaLoading = true)
            deleteUserDataUseCase.execute()
            deleteDialog = DeleteDialogViewState(ctaLoading = false)
        }
    }

    private fun handleCancelDeleteAccountClick() {
        deleteDialog = null
    }

    private fun logEvent(
        event: String,
        params: Map<String, String> = emptyMap(),
    ) {
        analytics.logEvent(
            source = Source.Settings,
            event = event,
            params = params,
        )
    }
}