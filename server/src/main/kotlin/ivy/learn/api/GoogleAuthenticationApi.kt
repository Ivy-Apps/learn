package ivy.learn.api

import IvyConstants
import arrow.core.raise.ensureNotNull
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.IvyUrls
import ivy.learn.ServerMode
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointBase
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.domain.auth.GoogleAuthorizationCode
import java.util.*

class GoogleAuthenticationApi(
    private val serverMode: ServerMode,
) : Api {
    override fun Routing.endpoints() {
        googleAuthCallback()
    }

    private fun Routing.googleAuthCallback() {
        getEndpointBase(IvyConstants.GoogleAuthCallbackEndpoint) { call ->
            val googleAuthCode = call.parameters["code"]?.let(::GoogleAuthorizationCode)
            ensureNotNull(googleAuthCode) {
                BadRequest("Google authorization code is required as 'code' parameter.")
            }
            // TODO: 1. Validate authorization code
            // TODO: 2. Created session token
            val sessionToken = UUID.randomUUID().toString()
            val frontEndUrl = if (serverMode.devMode) {
                IvyUrls.devFrontEnd
            } else {
                IvyUrls.frontEnd
            }
            call.respondRedirect("${frontEndUrl}?${IvyConstants.SessionTokenParam}=$sessionToken")
        }
    }

}