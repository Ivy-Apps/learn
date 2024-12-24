import io.ktor.client.*
import io.ktor.client.engine.java.*

class JVMPlatform: Platform {
    override val name: String = "jvm"

    override fun log(level: LogLevel, msg: String) {
        println("${level.name}: $msg")
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Java) {
        config(this)
    }

    override fun playSound(soundUrl: String) {
        // TODO: Implement
    }

    override fun getUrlParam(key: String): String? {
        return null
    }

    override fun setSocialPreview(title: String, description: String, imageUrl: String, pageUrl: String) {
        // n/a
    }
}

actual fun platform(): Platform = JVMPlatform()