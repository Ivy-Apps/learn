package ivy.di

import LogLevel
import Platform
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import ivy.data.HerokuServerUrlProvider
import ivy.data.LocalServerUrlProvider
import ivy.data.LottieAnimationLoader
import ivy.data.source.*
import ivy.di.Di.singleton
import ivy.di.autowire.autoWire
import ivy.model.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import platform
import io.ktor.client.plugins.logging.LogLevel as KtorLogLevel

object SharedModule : Di.Module {

    override fun init() = Di.appScope {
        singleton<Platform> { platform() }
        json()
        ktorClient()
        autoWire(::HerokuServerUrlProvider)
        autoWire(::LocalServerUrlProvider)
        autoWire(::LessonDataSource)
        autoWire(::TopicsDataSource)
        autoWire(::CoursesDataSource)
        autoWire(::LottieAnimationLoader)
        autoWire(::UserDataSource)
        autoWire(::AnalyticsDataSource)
    }

    private fun Di.Scope.json() = singleton {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
            explicitNulls = false
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
                    subclass(TextItem.serializer())
                }
            }
        }
    }

    private fun Di.Scope.ktorClient() = singleton {
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