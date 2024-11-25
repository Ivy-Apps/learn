package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.data.source.model.CourseResponse
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.model.CourseId

class CoursesApi(
    private val coursesRepository: CoursesRepository,
    private val lessonsRepository: LessonsRepository,
) : Api {
    override fun Routing.endpoints() {
        courseBy()
    }

    private fun Routing.courseBy() {
        getEndpoint("/courses/{id}") { params ->
            val courseId = params["id"]?.let(::CourseId)
            ensureNotNull(courseId) { BadRequest("Course id is required.") }
            val course = coursesRepository.fetchCourseById(courseId)
            ensureNotNull(course) { BadRequest("Course not found for id $courseId") }
            val lessons = lessonsRepository.fetchPartialLessons(courseId)
                .mapLeft { BadRequest(it) }.bind()
            CourseResponse(
                course = course,
                lessons = lessons
            )
        }
    }
}