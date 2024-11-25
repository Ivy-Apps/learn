package ui.screen.settings

import androidx.compose.runtime.Immutable

@Immutable
class SettingsViewState

sealed interface SettingsViewEvent {
    data object OnBackClick : SettingsViewEvent
}