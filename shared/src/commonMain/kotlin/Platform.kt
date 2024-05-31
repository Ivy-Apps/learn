import io.ktor.client.*

interface Platform {
    val name: String
    fun log(level: LogLevel, msg: String)
    fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient
    fun playSound(soundUrl: String)
}

enum class LogLevel {
    Debug,
    Info,
    Error,
}

expect fun platform(): Platform