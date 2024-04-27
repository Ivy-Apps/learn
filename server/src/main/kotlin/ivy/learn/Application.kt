package ivy.learn

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ivy.di.Di
import ivy.di.SharedModule
import ivy.learn.data.di.DataModule
import ivy.learn.di.AppModule

fun main() {
    Di.init(modules = setOf(SharedModule, DataModule, AppModule))
    val app = Di.get<LearnApp>()

    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0",
        module = { app.init(this) },
    ).start(wait = true)
}