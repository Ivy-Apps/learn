package ivy.api

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorResponse(
    val message: String,
    val resolution: ServerErrorResolution?,
)

@Serializable
enum class ServerErrorResolution {
    Login,
}