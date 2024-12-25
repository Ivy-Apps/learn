package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.data.sessionToken
import ivy.data.source.model.CourseResponse
import ivy.learn.CourseId
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.api.common.postEndpointAuthenticated
import ivy.learn.domain.CourseService
import ivy.model.course.CourseProgressRequest

class CoursesApi(
    private val courseService: CourseService,
) : Api {
    override fun Routing.endpoints() {
        loadCourse()
        saveCourseProgress()
    }

    private fun Routing.loadCourse() {
        getEndpoint<CourseResponse>("/courses/{id}") { headers, params ->
            val courseId = params["id"]?.let(::CourseId)
            ensureNotNull(courseId) { BadRequest("Course id is required.") }
            courseService.loadCourse(
                sessionToken = headers.sessionToken(),
                courseId = courseId,
            ).bind()
        }
    }

    private fun Routing.saveCourseProgress() {
        postEndpointAuthenticated<CourseProgressRequest, Unit>(
            "/lessons/{courseId}/progress"
        ) { params, body, sessionToken ->
            val courseId = params["courseId"]?.let(::CourseId)
            ensureNotNull(courseId) { BadRequest("Course id is missing!") }
            courseService.saveCourseProgress(
                sessionToken = sessionToken,
                courseId = courseId,
                lessonId = body.completedLesson,
            ).bind()
        }
    }
}