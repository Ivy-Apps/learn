import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

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
}


actual fun platform(): Platform = IOSPlatform()