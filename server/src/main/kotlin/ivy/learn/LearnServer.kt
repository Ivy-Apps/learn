package ivy.learn

import arrow.core.Either
import arrow.core.raise.either
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.api.*
import ivy.learn.data.database.Database
import kotlinx.serialization.json.Json

class LearnServer(
    private val database: Database,
    private val configurationProvider: ServerConfigurationProvider,
) {
    private val apis by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>(),
            Di.get<StatusApi>(),
            Di.get<CoursesApi>(),
            Di.get<TopicsApi>(),
        )
    }

    private fun injectDependencies() = Di.appScope {
        register { AnalyticsApi() }
        register { LessonsApi(Di.get()) }
        register { StatusApi() }
        register { TopicsApi(Di.get(), Di.get()) }
        register { CoursesApi(Di.get(), Di.get()) }
    }

    fun init(ktorApp: Application): Either<String, Unit> = either {
        with(ktorApp) {
            configureCORS()
            configureContentNegotiation()
        }
        val config = configurationProvider.fromEnvironment().bind()
        Di.appScope { register { config } }
        database.init(config.database).bind()

        injectDependencies()
        ktorApp.routing {
            apis.forEach { api ->
                with(api) { endpoints() }
            }
        }
    }

    private fun Application.configureContentNegotiation() {
        install(ContentNegotiation) {
            json(json = Di.get<Json>())
        }
    }

    private fun Application.configureCORS() {
        install(CORS) {
            methods.add(HttpMethod.Options)
            methods.add(HttpMethod.Put)
            methods.add(HttpMethod.Delete)
            methods.add(HttpMethod.Patch)
            headers.add(HttpHeaders.Authorization)
            headers.add(HttpHeaders.ContentType)
            headers.add(HttpHeaders.AccessControlAllowOrigin)
            headers.add(HttpHeaders.AccessControlAllowHeaders)
            headers.add(HttpHeaders.AccessControlAllowMethods)
            anyHost()
        }
    }
}