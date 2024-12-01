package ivy.learn.api.common.model

sealed interface ServerError {
    val msg: String

    data class BadRequest(override val msg: String) : ServerError
    data class Unknown(override val msg: String) : ServerError
    data object Unauthorized : ServerError {
        override val msg: String = "You don't have access to this resource or operation."
    }

    data object SessionExpired : ServerError {
        override val msg: String = "Your session has expired. Please log in again."
    }
}