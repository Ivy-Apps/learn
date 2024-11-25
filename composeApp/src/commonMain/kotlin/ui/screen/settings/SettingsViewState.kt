package ui.screen.settings

import androidx.compose.runtime.Immutable
import ui.screen.course.CourseViewEvent

@Immutable
class SettingsViewState

sealed interface SettingsViewEvent {
    data object OnBackClick : SettingsViewEvent
}