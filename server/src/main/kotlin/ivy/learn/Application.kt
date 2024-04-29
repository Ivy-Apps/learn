package ivy.learn

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import ivy.di.Di
import ivy.di.SharedModule
import ivy.learn.data.di.DataModule
import ivy.learn.di.AppModule
import kotlinx.serialization.json.Json

fun main() {
    Di.init(modules = setOf(SharedModule, DataModule, AppModule))
    val app = Di.get<LearnServer>()

    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0",
        module = {
            configureSever()
            app.init(this)
        },
    ).start(wait = true)
}

private fun Application.configureSever() {
    install(ContentNegotiation) {
        json(Di.get<Json>())
    }
}