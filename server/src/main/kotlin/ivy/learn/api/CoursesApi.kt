package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.data.repository.CoursesRepository
import ivy.model.CourseId

class CoursesApi(
    private val coursesRepository: CoursesRepository,
) : Api {
    override fun Routing.endpoints() {
        courseBy()
    }

    private fun Routing.courseBy() {
        get("/courses/{id}", endpoint { params ->
            val courseId = params["id"]?.let(::CourseId)
            ensureNotNull(courseId) { BadRequest("Course id is required.") }
            val course = coursesRepository.fetchCourseById(courseId)
            ensureNotNull(course) { BadRequest("Course not found for id $courseId") }
        })
    }
}