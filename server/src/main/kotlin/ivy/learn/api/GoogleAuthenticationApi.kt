package ivy.learn.api

import IvyConstants
import arrow.core.raise.ensureNotNull
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.IvyUrls
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointBase
import ivy.learn.api.common.model.ServerError.BadRequest
import java.util.*

class GoogleAuthenticationApi : Api {
    override fun Routing.endpoints() {
        googleAuthCallback()
    }

    private fun Routing.googleAuthCallback() {
        get(IvyConstants.GoogleAuthCallbackEndpoint) {
            call.parameters[""]
        }
        getEndpointBase(IvyConstants.GoogleAuthCallbackEndpoint) { call ->
            val googleAuthCode = call.parameters["code"]?.let(::GoogleAuthorizationCode)
            ensureNotNull(googleAuthCode) {
                BadRequest("Google authorization code is required as 'code' parameter.")
            }
            // TODO: 1. Validate authorization code
            // TODO: 2. Created session token
            val sessionToken = UUID.randomUUID().toString()
            val frontEndUrl = IvyUrls.debugFrontEnd
            call.respondRedirect("${frontEndUrl}?${IvyConstants.SessionTokenParam}=$sessionToken")
        }
    }

    @JvmInline
    value class GoogleAuthorizationCode(val value: String)
}