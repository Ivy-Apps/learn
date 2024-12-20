import io.ktor.client.*
import io.ktor.client.engine.darwin.*

class IOSPlatform : Platform {
    override val name: String = "ios"

    override fun log(level: LogLevel, msg: String) {
        println("${level.name}: $msg")
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Darwin) {
        config(this)
    }

    override fun playSound(soundUrl: String) {
        // TODO: Implement
    }

    override fun getUrlParam(key: String): String? {
        return null
    }
}


actual fun platform(): Platform = IOSPlatform()