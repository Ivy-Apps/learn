package ui.screen.course

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import ivy.model.CourseId
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.screen.course.composable.CourseContent
import ui.screen.course.mapper.CourseViewStateMapper

object CourseRouter : Router<CourseScreen> {
    const val PATH = "course"
    const val COURSE_ID = "courseId"
    const val COURSE_NAME = "courseName"

    override fun fromRoute(route: Route): Option<CourseScreen> = option {
        ensure(route.path == PATH)
        CourseScreen(
            courseId = route[COURSE_ID].bind().let(::CourseId),
            courseName = route[COURSE_NAME].bind(),
        )
    }

    override fun toRoute(screen: CourseScreen): Route {
        TODO("Not yet implemented")
    }
}

class CourseScreen(
    private val courseId: CourseId,
    private val courseName: String,
) : Screen() {
    override fun toRoute(): Route = CourseRouter.toRoute(this)

    override fun onDi(): Di.Scope.() -> Unit = {
        autoWire(::CourseViewStateMapper)
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