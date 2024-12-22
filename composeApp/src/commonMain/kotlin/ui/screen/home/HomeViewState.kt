package ui.screen.home

import androidx.compose.runtime.Immutable
import ivy.learn.CourseId
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeViewState(
    val items: ImmutableList<HomeItemViewState>
)

@Immutable
sealed interface HomeItemViewState {
    data class Section(val title: String) : HomeItemViewState
    data class Course(
        val id: String,
        val imageUrl: String,
        val name: String,
        val tagline: String,
        val progress: Float,
    ) : HomeItemViewState
}

sealed interface HomeViewEvent {
    data class OnCourseClick(
        val id: CourseId,
        val name: String,
    ) : HomeViewEvent

    data object OnSettingsClick : HomeViewEvent
}