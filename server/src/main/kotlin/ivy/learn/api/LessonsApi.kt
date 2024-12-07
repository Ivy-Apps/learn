package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointAuthenticated
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.domain.auth.AuthenticationService
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsApi(
    private val repository: LessonsRepository,
    private val authService: AuthenticationService,
) : Api {
    override fun Routing.endpoints() {
        lessonById()
    }

    private fun Routing.lessonById() {
        getEndpointAuthenticated<Lesson>("/lessons/{courseId}/{lessonId}") { params, sessionToken ->
            val courseId = params["courseId"]?.let(::CourseId)
            val lessonId = params["lessonId"]?.let(::LessonId)
            ensureNotNull(courseId) { BadRequest("Course id is missing!") }
            ensureNotNull(lessonId) { BadRequest("Lesson id is missing!") }
            repository.fetchLesson(courseId, lessonId)
                .mapLeft(ServerError::Unknown).bind()
        }
    }
}