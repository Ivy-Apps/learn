package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.ServerMode
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import kotlinx.serialization.Serializable

class StatusApi(
    private val serverMode: ServerMode
) : Api {
    override fun Routing.endpoints() {
        getEndpoint("/status") {
            HelloResponse(
                message = "Hello, world! (devMode = ${serverMode.devMode})",
                time = System.currentTimeMillis(),
            )
        }
    }
}

@Serializable
data class HelloResponse(
    val message: String,
    val time: Long,
)