package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.data.source.model.CourseResponse
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointAuthenticated
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.domain.CourseService
import ivy.model.CourseId

class CoursesApi(
    private val courseService: CourseService,
) : Api {
    override fun Routing.endpoints() {
        courseBy()
    }

    private fun Routing.courseBy() {
        getEndpointAuthenticated<CourseResponse>("/courses/{id}") { params, sessionToken ->
            val courseId = params["id"]?.let(::CourseId)
            ensureNotNull(courseId) { BadRequest("Course id is required.") }
            courseService.loadCourse(
                sessionToken = sessionToken,
                courseId = courseId,
            ).bind()
        }
    }
}