package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Ktor: Hello from BE!")
        }
        get("/version") {
            call.respondText("1.0.0")
        }
    }
}