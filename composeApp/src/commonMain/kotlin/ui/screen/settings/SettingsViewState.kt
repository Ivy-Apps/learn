package ui.screen.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsViewState(
    val soundEnabled: Boolean,
    val deleteDialog: DeleteDialogViewState?
)

@Immutable
data class DeleteDialogViewState(
    val ctaLoading: Boolean
)

sealed interface SettingsViewEvent {
    data object OnBackClick : SettingsViewEvent
    data object OnPremiumClick : SettingsViewEvent
    data class OnSoundEnabledChange(val enabled: Boolean) : SettingsViewEvent
    data object OnPrivacyClick : SettingsViewEvent
    data object OnLogoutClick : SettingsViewEvent
    data object OnDeleteAccountClick : SettingsViewEvent
    data object OnTermsOfUseClick : SettingsViewEvent
    data object OnPrivacyPolicyClick : SettingsViewEvent
    data object OnConfirmDeleteAccountClick : SettingsViewEvent
    data object OnCancelDeleteAccountClick : SettingsViewEvent
}