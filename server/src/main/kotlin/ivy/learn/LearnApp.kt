package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.api.AnalyticsApi
import ivy.learn.api.Api
import ivy.learn.data.database.ExposedDatabase

class LearnApp(
    private val database: ExposedDatabase
) {
    private val apis by lazy {
        setOf<Api>(
            Di.get<AnalyticsApi>()
        )
    }

    private fun onDi() = Di.appScope {
        register { AnalyticsApi() }
    }

    fun init(ktorApp: Application) {
        database.init().onLeft {
            throw InitializationError("Failed to initialize database: $it")
        }
        onDi()

        ktorApp.routing {
            apis.forEach { api ->
                with(api) { endpoints() }
            }
        }
    }
}

class InitializationError(message: String) : Exception(message)