package ui.screen.course

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ivy.model.CourseId
import ui.navigation.Screen
import ui.screen.course.composable.CourseContent
import ui.screen.course.mapper.CourseViewStateMapper

class CourseScreen(
    private val courseId: CourseId,
    private val courseName: String,
) : Screen() {
    override val path: String = "course"

    override fun onDi(): Di.Scope.() -> Unit = {
        register { CourseViewStateMapper() }
        register {
            CourseViewModel(
                courseId = courseId,
                courseName = courseName,
                navigation = Di.get(),
                repository = Di.get(),
                mapper = Di.get(),
            )
        }
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