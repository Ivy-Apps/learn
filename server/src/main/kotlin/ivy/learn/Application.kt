package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.learn.data.di.DataModule
import ivy.learn.di.AppModule

fun main() {
    Di.init(modules = setOf(DataModule, AppModule))

    val app = Di.get<LearnApp>()

    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0",
        module = {
            app.init()
        }
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