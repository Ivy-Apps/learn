package ivy.di

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import ivy.di.Di.singleton
import kotlinx.serialization.json.Json

object AppDiModule {
    fun init() {
        Di.appScope {
            json()
        }
    }

    private fun Di.DiScope.json() = singleton {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }


    private fun Di.DiScope.ktorClient(json: Json) = singleton {
        HttpClient {
            install(ContentNegotiation) {
                json(json)
            }

            install(Logging) {
                level = LogLevel.BODY
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
            }
        }
    }
}