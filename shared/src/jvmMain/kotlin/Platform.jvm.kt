import io.ktor.client.*
import io.ktor.client.engine.java.*

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"

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
}

actual fun platform(): Platform = JVMPlatform()