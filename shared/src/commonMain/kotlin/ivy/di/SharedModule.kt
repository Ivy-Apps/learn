package ivy.di

import LogLevel
import Platform
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import ivy.di.Di.singleton
import ivy.model.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
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
            coerceInputValues = true
            serializersModule = SerializersModule {
                polymorphic(LessonItem::class) {
                    subclass(ChoiceItem.serializer())
                    subclass(ImageItem.serializer())
                    subclass(LessonNavigationItem.serializer())
                    subclass(LinkItem.serializer())
                    subclass(LottieAnimationItem.serializer())
                    subclass(MysteryItem.serializer())
                    subclass(OpenQuestionItem.serializer())
                    subclass(QuestionItem.serializer())
                    subclass(TextContentItem.serializer())
                }
            }
        }
    }

    private fun Di.DiScope.ktorClient() = singleton {
        val platform = Di.get<Platform>()
        platform.httpClient {
            install(ContentNegotiation) {
                json(Di.get<Json>(), contentType = ContentType.Any)
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