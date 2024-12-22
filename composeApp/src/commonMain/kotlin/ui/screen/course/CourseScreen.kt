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
) : Screen() {
    override fun toRoute(): Route = CourseRouter.toRoute(this)

    override fun Di.Scope.onDi() {
        autoWire(::CourseViewStateMapper)
        register {
            CourseViewModel(
                courseId = courseId,
                courseName = courseName,
                navigation = Di.get(),
                repository = Di.get(),
                mapper = Di.get(),
                analytics = Di.get(),
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