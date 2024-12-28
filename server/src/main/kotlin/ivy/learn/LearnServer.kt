package ivy.learn

import IvyConstants
import arrow.core.Either
import arrow.core.raise.either
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.Di.singleton
import ivy.di.autowire.autoWire
import ivy.learn.api.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.KPIsApi
import ivy.learn.config.ServerConfigurationProvider
import ivy.learn.data.database.LearnDatabase
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

class LearnServer(
    private val database: LearnDatabase,
    private val configurationProvider: ServerConfigurationProvider,
    private val serverMode: ServerMode,
) {
    private val apis: Set<Api> by lazy {
        setOf(
            Di.get<AnalyticsApi>(),
            Di.get<LessonsApi>(),
            Di.get<StatusApi>(),
            Di.get<CoursesApi>(),
            Di.get<TopicsApi>(),
            Di.get<GoogleAuthenticationApi>(),
            Di.get<UsersApi>(),
            Di.get<MetricsApi>(),
            Di.get<KPIsApi>(),
        )
    }

    private fun injectDependencies() = Di.appScope {
        singleton<Logger> { LoggerFactory.getLogger("Application") }
        autoWire(::AnalyticsApi)
        autoWire(::MetricsApi)
        autoWire(::LessonsApi)
        autoWire(::StatusApi)
        autoWire(::TopicsApi)
        autoWire(::CoursesApi)
        autoWire(::GoogleAuthenticationApi)
        autoWire(::UsersApi)
        autoWire(::KPIsApi)
    }

    fun init(ktorApp: Application): Either<String, Unit> = either {
        with(ktorApp) {
            configureCORS()
            configureContentNegotiation()
            configureLogging()
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
            // Allow only necessary HTTP methods
            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Delete)
            allowMethod(HttpMethod.Patch)

            // Allow only essential headers
            allowHeader(HttpHeaders.ContentType)
            allowHeader(IvyConstants.HEADER_SESSION_TOKEN)

            // Environment-specific origin restrictions
            if (serverMode.devMode) {
                // Development: Allow all origins for flexibility
                anyHost()
            } else {
                // Production: Allow only your frontend origin
                anyHost() // easier development, not big security threat
//                allowHost(
//                    host = "ivylearn.app",
//                    schemes = listOf("https")
//                )
            }

            // Disable credentials (not needed for token-based auth)
            allowCredentials = false

            // Optional: Cache preflight requests for better performance
            maxAgeInSeconds = 3600L
        }
    }

    private fun Application.configureLogging() {
        install(CallLogging) {
            level = Level.INFO // Log INFO-level messages
            format { call ->
                val status = call.response.status() ?: "Unknown"
                val method = call.request.httpMethod.value
                val uri = call.request.uri
                "$method $uri -> $status"
            }
        }
    }
}