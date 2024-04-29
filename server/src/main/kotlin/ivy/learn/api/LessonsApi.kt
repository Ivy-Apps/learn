package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.learn.api.common.model.ServerError
import ivy.model.Lesson

class LessonsApi : Api {
    override fun Routing.endpoints() {
        // Endpoint that gets a lesson by ID
        get("/lessons/{id}") {
            endpoint<Lesson> { params ->
                val lessonId = params["id"]
                ensureNotNull(lessonId) { ServerError.BadRequest("\"lessons/{id}\" is required") }

                TODO()
            }
        }
    }
}