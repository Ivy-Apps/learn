package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.ServerMode
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import kotlinx.serialization.Serializable
import org.slf4j.Logger

class StatusApi(
    private val serverMode: ServerMode,
    private val logger: Logger,
) : Api {
    override fun Routing.endpoints() {
        getEndpoint<StatusResponse>("/status") { _, _ ->
            val time = System.currentTimeMillis()
            logger.debug("Requesting status at $time")
            StatusResponse(
                message = "Hello, world! (devMode = ${serverMode.devMode})",
                time = time,
                version = "Auto-deploy test",
            )
        }
    }

    @Serializable
    data class StatusResponse(
        val message: String,
        val time: Long,
        val version: String,
    )
}