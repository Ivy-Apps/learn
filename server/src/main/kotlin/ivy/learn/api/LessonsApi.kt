package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.LessonsRepository
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsApi(
    private val repository: LessonsRepository
) : Api {
    override fun Routing.endpoints() {
        // Endpoint that gets a lesson by ID
        get("/lessons/{id}", endpoint<Lesson> { params ->
            val lessonId = params["id"]?.let(::LessonId)
            ensureNotNull(lessonId) { ServerError.BadRequest("Lesson id is missing!") }
            repository.fetchLessonById(lessonId)
                .mapLeft(ServerError::Unknown).bind()
        })
    }
}