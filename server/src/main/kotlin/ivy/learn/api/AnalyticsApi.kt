package ivy.learn.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.learn.api.common.Api

class AnalyticsApi : Api {
    override fun Routing.endpoints() {
        get("/analytics/hello") {
            call.respondText("Hello, Analytics!")
        }
    }
}