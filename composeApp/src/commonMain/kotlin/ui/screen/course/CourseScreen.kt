package ui.screen.course

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ui.navigation.Screen
import ui.screen.course.composable.CourseContent

class CourseScreen() : Screen() {
    override val path: String = "course"

    override fun onDi(): Di.ScreenScope.() -> Unit = {
        register { CourseViewModel(Di.get()) }
    }

    private val viewModel: CourseViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        CourseContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}