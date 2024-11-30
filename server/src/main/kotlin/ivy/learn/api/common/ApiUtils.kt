package ivy.learn.api.common

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerErrorResponse


@IvyServerDsl
inline fun <reified Body : Any, reified Response : Any> Routing.postEndpoint(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(Body, Parameters) -> Response
) {
    postEndpointBase(path) { call ->
        val body = try {
            call.receive<Body>()
        } catch (e: Exception) {
            raise(ServerError.BadRequest("Malformed request body: $e"))
        }
        val response = handler(body, call.parameters)
        call.respond(HttpStatusCode.OK, response)
    }
}

@IvyServerDsl
inline fun <reified Response : Any> Routing.getEndpoint(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(Parameters) -> Response
) {
    getEndpointBase(path) { call ->
        val response = handler(call.parameters)
        call.respond(HttpStatusCode.OK, response)
    }
}

@IvyServerDsl
inline fun Routing.getEndpointBase(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(RoutingCall) -> Unit
) {
    get(path) {
        handleRequest(handler)
    }
}

@IvyServerDsl
inline fun Routing.postEndpointBase(
    path: String,
    crossinline handler: suspend Raise<ServerError>.(RoutingCall) -> Unit
) {
    post(path) {
        handleRequest(handler)
    }
}

suspend inline fun RoutingContext.handleRequest(
    crossinline handler: suspend Raise<ServerError>.(RoutingCall) -> Unit
) {
    val result = try {
        either<ServerError, Unit> {
            handler(call)
        }
    } catch (e: Throwable) {
        Either.Left(ServerError.Unknown("Unexpected error occurred: $e"))
    }
    result.onLeft { error ->
        respondError(error)
    }
}

@IvyServerDsl
suspend fun RoutingContext.respondError(error: ServerError) {
    call.respond(
        status = when (error) {
            is ServerError.BadRequest -> HttpStatusCode.BadRequest
            is ServerError.Unknown -> HttpStatusCode.InternalServerError
        },
        message = ServerErrorResponse(error.msg)
    )
}

@DslMarker
annotation class IvyServerDsl