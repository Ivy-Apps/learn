package ivy.learn.api.common

import arrow.core.raise.Raise
import arrow.core.raise.either
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerErrorResponse

inline fun <reified T : Any> endpoint(
    crossinline handler: suspend Raise<ServerError>.(Parameters) -> T
): suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit = {
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