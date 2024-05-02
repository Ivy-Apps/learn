import io.ktor.client.*
import io.ktor.client.engine.js.*

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"

    override fun log(level: LogLevel, msg: String) {
        console.log("${level.name}: $msg")
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Js) {
        config(this)
    }
}

actual fun platform(): Platform = JsPlatform()