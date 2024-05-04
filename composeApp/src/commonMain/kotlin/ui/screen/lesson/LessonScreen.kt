package ui.screen.lesson

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ivy.model.LessonId
import ui.navigation.Screen
import ui.screen.lesson.composable.LessonContent

class LessonScreen(
    id: LessonId,
    name: String
) : Screen() {
    override val path: String = "lesson"

    override fun onDi(): Di.ScreenScope.() -> Unit = {
        register { LessonViewModel(Di.get()) }
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