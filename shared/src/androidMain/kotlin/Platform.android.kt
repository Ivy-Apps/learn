import LogLevel.*
import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*

class AndroidPlatform : Platform {
    override val name: String = "android"

    override fun log(level: LogLevel, msg: String) {
        val tag = ""
        when (level) {
            Debug -> Log.d(tag, msg)
            Info -> Log.i(tag, msg)
            Error -> Log.e(tag, msg)
        }
    }

    override fun httpClient(
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient = HttpClient(Android) {
        config(this)
    }

    override fun playSound(soundUrl: String) {
        // TODO: Implement
    }
}

actual fun platform(): Platform = AndroidPlatform()