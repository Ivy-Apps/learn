package ui.screen.lesson

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import ivy.learn.CourseId
import ivy.learn.LessonId
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.ComposeViewModel
import ui.screen.lesson.composable.LessonContent
import ui.screen.lesson.handler.*
import ui.screen.lesson.mapper.LessonTreeManager
import ui.screen.lesson.mapper.LessonViewStateMapper
import kotlin.jvm.JvmInline

object LessonRouter : Router<LessonScreen> {
  const val PATH = "lesson"
  const val COURSE_ID = "course_id"
  const val LESSON_ID = "lesson_id"
  const val LESSON_NAME = "lesson_name"

  override fun fromRoute(route: Route): Option<LessonScreen> = option {
    ensure(route.path == PATH)
    LessonScreen(
      courseId = route[COURSE_ID].bind().let(::CourseId),
      lessonId = route[LESSON_ID].bind().let(::LessonId),
      lessonName = route[LESSON_NAME].bind()
    )
  }

  override fun toRoute(screen: LessonScreen): Route {
    return Route(
      path = PATH,
      params = mapOf(
        COURSE_ID to screen.courseId.value,
        LESSON_ID to screen.lessonId.value,
        LESSON_NAME to screen.lessonName,
      )
    )
  }

}

class LessonScreen(
  val courseId: CourseId,
  val lessonId: LessonId,
  val lessonName: String
) : Screen<LessonViewState, LessonViewEvent>() {
  override val name = "lesson"
  override fun toRoute(): Route = LessonRouter.toRoute(this)
  override fun getViewModel(affinity: Di.Scope): ComposeViewModel<LessonViewState, LessonViewEvent> {
    return Di.get<LessonViewModel>(affinity = affinity)
  }

  override fun Di.Scope.onDi() {
    autoWire(::LessonTreeManager)
    autoWire(::LessonViewStateMapper)
    autoWire(::OnBackClickEventHandler)
    autoWire(::OnContinueClickEventHandler)
    autoWire(::QuestionEventHandler)
    autoWire(::OnSoundClickEventHandler)
    autoWire(::OnFinishClickEventHandler)
    autoWire(::OnChoiceClickEventHandler)
    register {
      LessonViewModel.Args(
        courseId = courseId,
        lessonId = lessonId,
        lessonName = lessonName,
      )
    }
    register {
      EventHandlers(
        setOf(
          Di.get<OnBackClickEventHandler>(),
          Di.get<OnContinueClickEventHandler>(),
          Di.get<QuestionEventHandler>(),
          Di.get<OnSoundClickEventHandler>(),
          Di.get<OnFinishClickEventHandler>(),
          Di.get<OnChoiceClickEventHandler>(),
        )
      )
    }
    autoWire(::LessonViewModel)
  }

  @Composable
  override fun Content(viewState: LessonViewState, onEvent: (LessonViewEvent) -> Unit) {
    LessonContent(
      viewState = viewState,
      onEvent = onEvent
    )
  }

  @JvmInline
  value class EventHandlers(val value: Set<LessonEventHandler<*>>)
}