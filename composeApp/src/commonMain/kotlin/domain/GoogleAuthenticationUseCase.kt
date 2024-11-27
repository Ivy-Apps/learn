package domain

import IvyConstants
import androidx.compose.ui.platform.UriHandler
import ivy.data.ServerUrlProvider

class GoogleAuthenticationUseCaseImpl(
    private val uriHandler: UriHandler,
    private val serverUrlProvider: ServerUrlProvider,
) : GoogleAuthenticationUseCase {
    override fun loginWithGoogle() {
        val clientId = IvyConstants.GoogleClientId
        val redirectUri = "${serverUrlProvider.serverUrl}${IvyConstants.GoogleAuthCallbackEndpoint}"

        val authUrl = """
        https://accounts.google.com/o/oauth2/v2/auth?
        client_id=$clientId&
        redirect_uri=$redirectUri&
        response_type=code&
        scope=email profile
    """.trimIndent()

        uriHandler.openUri(authUrl)
    }
}

interface GoogleAuthenticationUseCase {
    fun loginWithGoogle()
}