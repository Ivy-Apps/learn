package ui.screen.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsViewState(
    val soundsEnabled: Boolean
)

sealed interface SettingsViewEvent {
    data object OnBackClick : SettingsViewEvent
    data class OnSoundsEnabledChange(val enabled: Boolean): SettingsViewEvent
}