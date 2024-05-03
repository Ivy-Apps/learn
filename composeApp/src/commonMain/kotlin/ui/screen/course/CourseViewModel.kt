package ui.screen.course

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import ui.ComposeViewModel
import ui.navigation.Navigation

class CourseViewModel(
    private val navigation: Navigation
) : ComposeViewModel<CourseViewState, CourseViewEvent> {
    @Composable
    override fun viewState(): CourseViewState {
        return CourseViewState(
            name = "Kotlin fundamentals",
            items = persistentListOf(
                CourseItemViewState.Lesson(
                    imageUrl = "https://cdn.pixabay.com/photo/2023/03/03/14/16/light-7827771_128" +
                            "0.jpg",
                    name = "ADTs",
                    tagLine = "Learn the power of modeling your data easily with algebraic data " +
                            "types",
                    progress = ProgressViewState.Completed
                ),
                CourseItemViewState.Arrow(
                    progress = ProgressViewState.ToDo
                ),
                CourseItemViewState.Lesson(
                    imageUrl = "https://cdn.pixabay.com/photo/2023/03/03/14/16/light-7827771_128" +
                            "0.jpg",
                    name = "ADTs - part 2",
                    tagLine = "Learn the power of modeling your data easily with algebraic data " +
                            "types",
                    progress = ProgressViewState.ToDo
                ),
                CourseItemViewState.Arrow(
                    progress = ProgressViewState.Upcoming
                ),
                CourseItemViewState.Lesson(
                    imageUrl = "https://cdn.pixabay.com/photo/2023/03/03/14/16/light-7827771_128" +
                            "0.jpg",
                    name = "ADTs - part 3",
                    tagLine = "Learn the power of modeling your data easily with algebraic data " +
                            "types",
                    progress = ProgressViewState.Upcoming
                )
            )
        )
    }

    override fun onEvent(event: CourseViewEvent) {
        when (event) {
            CourseViewEvent.OnBackClick -> handleBackClick()
            is CourseViewEvent.OnLessonClick -> handleLessonClick(event.lesson)
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }

    private fun handleLessonClick(lesson: CourseItemViewState.Lesson) {
        // TODO - navigate to lesson
    }
}