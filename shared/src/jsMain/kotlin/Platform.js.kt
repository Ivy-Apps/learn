import io.ktor.client.*
import io.ktor.client.engine.js.*
import org.w3c.dom.Audio

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

    override fun playSound(soundUrl: String) {
        val audio = Audio(soundUrl)
        audio.play()
    }
}

actual fun platform(): Platform = JsPlatform()