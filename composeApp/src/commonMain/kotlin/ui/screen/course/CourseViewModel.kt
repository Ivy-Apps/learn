package ui.screen.course

import androidx.compose.runtime.*
import arrow.core.identity
import data.CourseRepository
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.data.source.model.CourseResponse
import ivy.learn.LessonId
import ivy.model.analytics.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import navigation.AccessControl
import navigation.Navigation
import ui.ComposeViewModel
import ui.screen.course.mapper.CourseViewStateMapper
import ui.screen.lesson.LessonScreen

class CourseViewModel(
  args: CourseScreen.Args,
  private val navigation: Navigation,
  private val repository: CourseRepository,
  private val mapper: CourseViewStateMapper,
  private val analytics: Analytics,
  private val screenScope: CoroutineScope,
  private val accessControl: AccessControl,
) : ComposeViewModel<CourseViewState, CourseViewEvent> {
  private val courseId = args.courseId
  private val courseName = args.courseName

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
        params = params {
          courseId(courseId)
          courseName(courseName)
        }
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
    val lessonId = LessonId(event.id)
    logEvent(
      event = "click_lesson",
      params = params {
        courseId(courseId)
        lessonId(lessonId)
        lessonName(event.name)
      }
    )
    screenScope.launch {
      accessControl.ensureLoggedIn {
        navigation.navigateTo(
          LessonScreen(
            courseId = courseId,
            lessonId = lessonId,
            lessonName = event.name,
          )
        )
      }
    }

  }

  private fun logEvent(
    event: String,
    params: Map<String, String>? = null,
  ) {
    analytics.logEvent(
      source = Source.Course,
      event = event,
      params = params,
    )
  }
}