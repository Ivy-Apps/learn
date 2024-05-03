package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import io.ktor.util.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.learn.api.common.model.ServerError
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

    @KtorDsl
    private fun Routing.lessonById() {
        get("/lessons/{courseId}/{lessonId}", endpoint<Lesson> { params ->
            val courseId = params["courseId"]?.let(::CourseId)
            val lessonId = params["lessonId"]?.let(::LessonId)
            ensureNotNull(courseId) { ServerError.BadRequest("Course id is missing!") }
            ensureNotNull(lessonId) { ServerError.BadRequest("Lesson id is missing!") }
            repository.fetchLesson(courseId, lessonId)
                .mapLeft(ServerError::Unknown).bind()
        })
    }
}