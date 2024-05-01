import io.ktor.client.*

interface Platform {
    val name: String
    val debug: Boolean
    fun log(level: LogLevel, msg: String)
    fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient
}

enum class LogLevel {
    Debug,
    Info,
    Error,
}

expect fun platform(): Platform