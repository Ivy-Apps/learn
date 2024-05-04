package ui.screen.lesson

import androidx.compose.runtime.Immutable
import ivy.model.Lesson

@Immutable
data class LessonViewState(
    val lesson: Lesson
)

sealed interface LessonViewEvent {
    data object OnBackClick : LessonViewEvent
}