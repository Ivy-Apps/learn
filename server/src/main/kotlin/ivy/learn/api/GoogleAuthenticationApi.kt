package ivy.learn.api

import IvyConstants
import arrow.core.raise.ensureNotNull
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ivy.IvyUrls
import ivy.learn.ServerMode
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointBase
import ivy.learn.api.common.model.ServerError
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.domain.auth.AuthenticationService
import ivy.learn.domain.auth.GoogleAuthorizationCode

class GoogleAuthenticationApi(
    private val serverMode: ServerMode,
    private val authService: AuthenticationService,
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
            val auth = authService.authenticate(googleAuthCode)
                .mapLeft(ServerError::Unknown)
                .bind()
            val sessionToken = auth.session.token
            val frontEndUrl = if (serverMode.devMode) {
                IvyUrls.devFrontEnd
            } else {
                IvyUrls.frontEnd
            }
            call.respondRedirect("${frontEndUrl}?${IvyConstants.SessionTokenParam}=$sessionToken")
        }
    }

}