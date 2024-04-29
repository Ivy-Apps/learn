package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.api.AnalyticsApi
import ivy.learn.api.LessonsApi
import ivy.learn.data.database.ExposedDatabase

class LearnServer(
    private val database: ExposedDatabase
) {
    private val apis by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>()
        )
    }

    private fun onDi() = Di.appScope {
        register { AnalyticsApi() }
        register { LessonsApi() }
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