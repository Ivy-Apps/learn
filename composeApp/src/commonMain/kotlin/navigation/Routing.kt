package navigation

import arrow.core.Option
import ui.screen.course.CourseRouter
import ui.screen.home.HomeRouter
import ui.screen.intro.IntroRouter
import ui.screen.lesson.LessonRouter
import ui.screen.settings.SettingsRouter

object Routing {
    private val routers = setOf<Router<*>>(
        IntroRouter,
        HomeRouter,
        LessonRouter,
        CourseRouter,
        SettingsRouter
    )

    fun resolve(route: Route): Screen? {
        return routers.firstNotNullOfOrNull {
            it.fromRoute(route).getOrNull()
        }
    }
}

interface Router<S : Screen> {
    fun fromRoute(route: Route): Option<S>
    fun toRoute(screen: S): Route
}