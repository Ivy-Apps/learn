package ivy.learn.api

import io.ktor.server.application.*
import io.ktor.server.routing.*

class LessonsApi : Api {
    override fun Routing.endpoints() {
        // Endpoint that gets a lesson by ID
        get("/lessons/{id}") {
            val id = call.parameters["id"]
            TODO()
        }
    }
}