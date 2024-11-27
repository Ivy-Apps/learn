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
import ivy.di.autowire.autoWire
import ivy.learn.api.*
import ivy.learn.api.common.Api
import ivy.learn.config.ServerConfigurationProvider
import ivy.learn.data.database.Database
import kotlinx.serialization.json.Json

class LearnServer(
    private val database: Database,
    private val configurationProvider: ServerConfigurationProvider,
) {
    private val apis: Set<Api> by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>(),
            Di.get<StatusApi>(),
            Di.get<CoursesApi>(),
            Di.get<TopicsApi>(),
            Di.get<GoogleAuthenticationApi>(),
        )
    }

    private fun injectDependencies() = Di.appScope {
        autoWire(::AnalyticsApi)
        autoWire(::LessonsApi)
        autoWire(::StatusApi)
        autoWire(::TopicsApi)
        autoWire(::CoursesApi)
        autoWire(::GoogleAuthenticationApi)
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
            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Delete)
            allowMethod(HttpMethod.Patch)
            allowHeader(HttpHeaders.Authorization)
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowHeader(HttpHeaders.AccessControlAllowHeaders)
            allowHeader(HttpHeaders.AccessControlAllowMethods)
            anyHost()
        }
    }
}