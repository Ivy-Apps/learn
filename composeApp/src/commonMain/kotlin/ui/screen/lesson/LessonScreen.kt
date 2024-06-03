package ui.screen.lesson

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ivy.model.CourseId
import ivy.model.LessonId
import ui.navigation.Screen
import ui.screen.lesson.composable.LessonContent
import ui.screen.lesson.handler.*
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
        register { LessonViewStateMapper(Di.get(), Di.get()) }
        register { OnBackClickEventHandler(Di.get()) }
        register { OnContinueClickEventHandler(Di.get()) }
        register { QuestionViewEventHandler(Di.get()) }
        register { OnSoundClickEventHandler(Di.get()) }
        register { OnFinishClickEventHandler(Di.get(), Di.get()) }
        register { OnChoiceClickEventHandler() }
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
                    Di.get<OnSoundClickEventHandler>(),
                    Di.get<OnFinishClickEventHandler>(),
                    Di.get<OnChoiceClickEventHandler>(),
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