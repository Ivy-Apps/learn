package ui.screen.lesson

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ivy.model.CourseId
import ivy.model.LessonId
import ui.navigation.Screen
import ui.screen.lesson.composable.LessonContent
import ui.screen.lesson.handler.OnBackClickEventHandler
import ui.screen.lesson.handler.OnContinueClickEventHandler
import ui.screen.lesson.handler.QuestionViewEventHandler
import ui.screen.lesson.mapper.LessonTreeManager
import ui.screen.lesson.mapper.LessonViewStateMapper

class LessonScreen(
    private val courseId: CourseId,
    private val lessonId: LessonId,
    private val lessonName: String
) : Screen() {
    override val path: String = "lesson"

    override fun onDi(): Di.ScreenScope.() -> Unit = {
        register { LessonTreeManager() }
        register { LessonViewStateMapper(Di.get()) }
        register { OnBackClickEventHandler(Di.get()) }
        register { OnContinueClickEventHandler() }
        register { QuestionViewEventHandler() }
        register {
            LessonViewModel(
                courseId = courseId,
                lessonId = lessonId,
                lessonName = lessonName,
                screenScope = screenScope,
                repository = Di.get(),
                viewStateMapper = Di.get(),
                eventHandlers = setOf(
                    Di.get<OnBackClickEventHandler>(),
                    Di.get<OnContinueClickEventHandler>(),
                    Di.get<QuestionViewEventHandler>(),
                )
            )
        }
    }

    private val viewModel: LessonViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        LessonContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}