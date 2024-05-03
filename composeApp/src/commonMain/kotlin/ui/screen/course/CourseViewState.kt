package ui.screen.course

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class CourseViewState(
    val name: String,
    val items: ImmutableList<CourseItemViewState>
)

@Immutable
sealed interface CourseItemViewState {
    data class Arrow(val progress: ProgressViewState) : CourseItemViewState
    data class Lesson(
        val name: String,
        val tagLine: String,
        val progress: ProgressViewState
    ) : CourseItemViewState
}

@Immutable
enum class ProgressViewState {
    Completed,
    ToDo,
    Upcoming
}

sealed interface CourseViewEvent {
    data object OnBackClick : CourseViewEvent
    data class OnLessonClick(val lesson: CourseItemViewState.Lesson) : CourseViewEvent
}