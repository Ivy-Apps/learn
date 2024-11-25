package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.data.repository.LessonsRepository
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsApi(
    private val repository: LessonsRepository
) : Api {
    override fun Routing.endpoints() {
        lessonById()
    }

    private fun Routing.lessonById() {
        getEndpoint<Lesson>("/lessons/{courseId}/{lessonId}") { params ->
            val courseId = params["courseId"]?.let(::CourseId)
            val lessonId = params["lessonId"]?.let(::LessonId)
            ensureNotNull(courseId) { BadRequest("Course id is missing!") }
            ensureNotNull(lessonId) { BadRequest("Lesson id is missing!") }
            repository.fetchLesson(courseId, lessonId)
                .mapLeft(ServerError::Unknown).bind()
        }
    }
}