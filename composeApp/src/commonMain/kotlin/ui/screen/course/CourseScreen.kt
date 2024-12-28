package ui.screen.course

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import ivy.learn.CourseId
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.course.composable.CourseContent
import ui.screen.course.mapper.CourseViewStateMapper

object CourseRouter : Router<CourseScreen> {
  const val PATH = "course"
  const val COURSE_ID = "course_id"
  const val COURSE_NAME = "course_name"

  override fun fromRoute(route: Route): Option<CourseScreen> = option {
    ensure(route.path == PATH)
    CourseScreen(
      courseId = route[COURSE_ID].bind().let(::CourseId),
      courseName = route[COURSE_NAME].bind(),
    )
  }

  override fun toRoute(screen: CourseScreen): Route {
    return Route(
      path = PATH,
      params = mapOf(
        COURSE_ID to screen.courseId.value,
        COURSE_NAME to screen.courseName,
      )
    )
  }
}

class CourseScreen(
  val courseId: CourseId,
  val courseName: String,
) : Screen<CourseViewState, CourseViewEvent>() {
  override val name = "course"
  override fun toRoute(): Route = CourseRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<CourseViewState, CourseViewEvent> {
    return Di.get<CourseViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::CourseViewStateMapper)
    register {
      Args(
        courseId = courseId,
        courseName = courseName,
      )
    }
    autoWire(::CourseViewModel)
  }

  @Composable
  override fun Content(viewState: CourseViewState, onEvent: (CourseViewEvent) -> Unit) {
    CourseContent(
      viewState = viewState,
      onEvent = onEvent,
    )
  }

  data class Args(
    val courseId: CourseId,
    val courseName: String,
  )
}