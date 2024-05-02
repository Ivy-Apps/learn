import io.ktor.client.*
import io.ktor.client.engine.java.*

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val debug: Boolean = false

    override fun log(level: LogLevel, msg: String) {
        println("${level.name}: $msg")
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Java) {
        config(this)
    }
}

actual fun platform(): Platform = JVMPlatform()