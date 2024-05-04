package ui.screen.course

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import arrow.core.identity
import data.CourseRepository
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId
import ivy.model.LessonId
import kotlinx.collections.immutable.persistentListOf
import ui.ComposeViewModel
import ui.navigation.Navigation
import ui.screen.course.mapper.CourseViewStateMapper
import ui.screen.lesson.LessonScreen

class CourseViewModel(
    private val courseId: CourseId,
    private val courseName: String,
    private val navigation: Navigation,
    private val repository: CourseRepository,
    private val mapper: CourseViewStateMapper,
) : ComposeViewModel<CourseViewState, CourseViewEvent> {

    private var courseResponse by mutableStateOf<CourseResponse?>(null)

    @Composable
    override fun viewState(): CourseViewState {
        LaunchedEffect(Unit) {
            courseResponse = repository.fetchCourse(courseId).fold(
                ifLeft = { null },
                ifRight = ::identity,
            )
        }
        return with(mapper) { courseResponse?.toViewState() }
            ?: CourseViewState(
                name = courseName,
                items = persistentListOf()
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
        navigation.navigate(
            LessonScreen(
                id = LessonId("4579"),
                name = "Lesson 1"
            )
        )
    }
}