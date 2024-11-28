package ui.screen.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsViewState(
    val soundsEnabled: Boolean,
    val deleteDialogVisible: Boolean,
)

sealed interface SettingsViewEvent {
    data object OnBackClick : SettingsViewEvent
    data object OnPremiumClick : SettingsViewEvent
    data class OnSoundsEnabledChange(val enabled: Boolean) : SettingsViewEvent
    data object OnPrivacyClick : SettingsViewEvent
    data object OnDeleteAccountClick : SettingsViewEvent
    data object OnTermsOfUseClick : SettingsViewEvent
    data object OnPrivacyPolicyClick : SettingsViewEvent
    data object OnConfirmDeleteAccountClick : SettingsViewEvent
    data object OnCancelDeleteAccountClick : SettingsViewEvent
}