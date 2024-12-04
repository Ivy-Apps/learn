package ui.screen.course

import androidx.compose.runtime.*
import arrow.core.identity
import data.CourseRepository
import domain.analytics.Analytics
import domain.analytics.Param
import domain.analytics.Source
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId
import ivy.model.LessonId
import kotlinx.collections.immutable.persistentListOf
import navigation.Navigation
import ui.ComposeViewModel
import ui.screen.course.mapper.CourseViewStateMapper
import ui.screen.lesson.LessonScreen

class CourseViewModel(
    private val courseId: CourseId,
    private val courseName: String,
    private val navigation: Navigation,
    private val repository: CourseRepository,
    private val mapper: CourseViewStateMapper,
    private val analytics: Analytics,
) : ComposeViewModel<CourseViewState, CourseViewEvent> {

    private var courseResponse by mutableStateOf<CourseResponse?>(null)

    @Composable
    override fun viewState(): CourseViewState {
        LaunchedEffect(Unit) {
            courseResponse = repository.fetchCourse(courseId).fold(
                ifLeft = { null },
                ifRight = ::identity,
            )
            logEvent(
                event = "view",
                params = mapOf(
                    Param.CourseId to courseId.value,
                    Param.CourseName to courseName,
                )
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
        navigation.navigateBack()
    }

    private fun handleLessonClick(event: CourseItemViewState.Lesson) {
        logEvent(
            event = "click_lesson",
            params = mapOf(
                Param.CourseId to courseId.value,
                Param.LessonId to event.id,
                Param.LessonName to event.name,
            )
        )
        navigation.navigateTo(
            LessonScreen(
                courseId = courseId,
                lessonId = LessonId(event.id),
                lessonName = event.name,
            )
        )
    }

    private fun logEvent(
        event: String,
        params: Map<String, String> = emptyMap(),
    ) {
        analytics.logEvent(
            source = Source.Course,
            event = event,
            params = params,
        )
    }
}