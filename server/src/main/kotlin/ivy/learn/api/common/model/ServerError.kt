package ivy.learn.api.common.model

import kotlinx.serialization.Serializable

sealed interface ServerError {
    val msg: String

    data class BadRequest(override val msg: String) : ServerError
    data class Unknown(override val msg: String) : ServerError
}

@Serializable
data class ServerErrorResponse(
    val message: String,
)