package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.learn.api.AnalyticsApi
import ivy.learn.api.Api
import ivy.learn.data.database.ExposedDatabase

class LearnApp(
    private val database: ExposedDatabase
) {
    private val apis = setOf<Api>(
        Di.get<AnalyticsApi>()
    )

    fun Application.init() {
        database.init().onLeft {
            throw InitializationError("Failed to initialize database: $it")
        }

        routing {
            apis.forEach { it.endpoints() }
        }
    }
}

class InitializationError(message: String) : Exception(message)