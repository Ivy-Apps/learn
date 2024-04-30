package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import kotlinx.serialization.Serializable

class StatusApi : Api {
    override fun Routing.endpoints() {
        get("/hello", endpoint {
            HelloResponse(
                message = "Hello, world!",
                time = System.currentTimeMillis(),
            )
        })
    }
}

@Serializable
data class HelloResponse(
    val message: String,
    val time: Long,
)