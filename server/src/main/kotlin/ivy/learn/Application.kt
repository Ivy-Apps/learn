package ivy.learn

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ivy.di.Di
import ivy.di.SharedModule
import ivy.learn.data.di.DataModule
import ivy.learn.di.AppModule
import ivy.learn.domain.di.DomainModule

fun main(args: Array<String>) {
    val devMode = "dev" in args
    initDi(devMode = devMode)
    val server = Di.get<LearnServer>()

    val port = System.getenv("PORT")?.toInt() ?: 8081
    println("Starting server on port $port...")
    if (devMode) {
        println("[WARNING][DEV MODE] Server running in dev mode!")
    }
    embeddedServer(
        Netty,
        port = port,
        host = "0.0.0.0",
        module = {
            server.init(this).onLeft {
                throw ServerInitializationException(reason = it)
            }
        },
    ).start(wait = true)
}

fun initDi(devMode: Boolean) {
    Di.init(
        SharedModule,
        DataModule,
        AppModule(devMode = devMode),
        DomainModule,
    )
}

@JvmInline
value class ServerMode(val devMode: Boolean)

class ServerInitializationException(reason: String) : Exception("Server initialization failed: $reason")
