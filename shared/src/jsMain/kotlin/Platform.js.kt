import io.ktor.client.*
import io.ktor.client.engine.js.*
import kotlinx.browser.window
import org.w3c.dom.Audio
import org.w3c.dom.url.URLSearchParams

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"

    override fun log(level: LogLevel, msg: String) {
        console.log("${level.name.uppercase()}: $msg")
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

    override fun getUrlParam(key: String): String? {
        val urlParams = URLSearchParams(window.location.search)
        return urlParams.get(key)
    }
}

actual fun platform(): Platform = JsPlatform()