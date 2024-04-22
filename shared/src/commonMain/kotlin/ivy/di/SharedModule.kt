package ivy.di

import LogLevel
import Platform
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import ivy.di.Di.singleton
import kotlinx.serialization.json.Json
import platform
import io.ktor.client.plugins.logging.LogLevel as KtorLogLevel

object SharedModule : DiModule {

    override fun init() = Di.appScope {
        singleton { platform() }
        json()
        ktorClient()
    }

    private fun Di.DiScope.json() = singleton {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    private fun Di.DiScope.ktorClient() = singleton {
        val platform = Di.get<Platform>()
        platform.httpClient {
            install(ContentNegotiation) {
                json(Di.get<Json>())
            }

            install(Logging) {
                level = KtorLogLevel.BODY
                logger = object : Logger {
                    override fun log(message: String) {
                        platform.log(LogLevel.Debug, message)
                    }
                }
            }
        }
    }
}