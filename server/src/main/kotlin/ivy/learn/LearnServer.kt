package ivy.learn

import arrow.core.Either
import arrow.core.raise.either
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
    private val configurationProvider: ServerConfigurationProvider,
) {
    private val apis by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>(),
            Di.get<StatusApi>()
        )
    }

    private fun injectDependencies() = Di.appScope {
        register { AnalyticsApi() }
        register { LessonsApi(Di.get()) }
        register { StatusApi() }
    }

    fun init(ktorApp: Application): Either<String, Unit> = either {
        val config = configurationProvider.fromEnvironment().bind()
        database.init(config.database).bind()

        injectDependencies()
        ktorApp.routing {
            apis.forEach { api ->
                with(api) { endpoints() }
            }
        }
    }
}