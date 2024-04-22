class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"

    override fun log(level: LogLevel, msg: String) {
        println("${level.name}: $msg")
    }

    override fun httpClient(config: HttpClientConfig<*>.() -> Unit): Htt {
        TODO("Not yet implemented")
    }
}

actual fun platform(): Platform = WasmPlatform()