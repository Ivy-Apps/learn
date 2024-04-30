package ivy.learn

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.api.AnalyticsApi
import ivy.learn.api.LessonsApi
import ivy.learn.api.StatusApi
import ivy.learn.data.database.Database

class LearnServer(
    private val database: Database,
    private val devMode: Boolean,
) {
    private val apis by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>(),
            Di.get<StatusApi>()
        )
    }

    private fun onDi() = Di.appScope {
        register { AnalyticsApi() }
        register { LessonsApi(Di.get()) }
        register { StatusApi() }
    }

    fun init(ktorApp: Application) {
        database.init().onLeft {
            if (!devMode) {
                throw InitializationError("Failed to initialize database: $it")
            }
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