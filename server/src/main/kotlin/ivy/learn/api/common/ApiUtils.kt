package ivy.learn.api.common

import arrow.core.raise.Raise
import arrow.core.raise.either
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerErrorResponse

@IvyServerDsl
inline fun <reified T : Any> Routing.getEndpoint(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(Parameters) -> T
) {
    get(path) {
        either {
            handler(call.parameters)
        }.onLeft { error ->
            call.respond(
                status = when (error) {
                    is ServerError.BadRequest -> HttpStatusCode.BadRequest
                    is ServerError.Unknown -> HttpStatusCode.InternalServerError
                },
                message = ServerErrorResponse(error.msg)
            )
        }.onRight { response ->
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

@IvyServerDsl
inline fun Routing.getEndpointBase(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(RoutingCall) -> Unit
) {
    get(path) {
        either {
            handler(call)
        }.onLeft { error ->
            call.respond(
                status = when (error) {
                    is ServerError.BadRequest -> HttpStatusCode.BadRequest
                    is ServerError.Unknown -> HttpStatusCode.InternalServerError
                },
                message = ServerErrorResponse(error.msg)
            )
        }
    }
}

@DslMarker
annotation class IvyServerDsl