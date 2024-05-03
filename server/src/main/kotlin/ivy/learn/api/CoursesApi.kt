package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.model.Course
import ivy.model.CourseId
import ivy.model.LessonId

class CoursesApi : Api {
    override fun Routing.endpoints() {
        courses()
    }

    private fun Routing.courses() {
        get("/courses", endpoint {
            listOf(
                Course(
                    id = CourseId("programming_fundamentals_101"),
                    name = "Programming Fundamentals 101",
                    tagline = "",
                    icon = null,
                    lessons = listOf(
                        LessonId("what_is_programming"),
                    )
                ),
            )
        })
    }
}