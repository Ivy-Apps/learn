import io.ktor.client.*
import io.ktor.client.engine.js.*

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"

    override fun log(level: LogLevel, msg: String) {
        println("${level.name}: $msg")
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Js) {
        config(this)
    }
}

actual fun platform(): Platform = WasmPlatform()