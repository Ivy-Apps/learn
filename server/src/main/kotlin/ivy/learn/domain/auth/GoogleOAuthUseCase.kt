package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.learn.config.ServerConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.slf4j.Logger

class GoogleOAuthUseCase(
    private val config: ServerConfiguration,
    private val httpClient: HttpClient,
    private val logger: Logger,
) {

    suspend fun verify(
        authCode: GoogleAuthorizationCode
    ): Either<String, GooglePublicProfile> = either {
        ensure(authCode.value.isNotBlank()) {
            "Google authorization code is blank!"
        }
        val tokenResponse = exchangeAuthCodeForTokens(authCode).bind()
        val userInfoResponse = fetchUserInfo(accessToken = tokenResponse.accessToken).bind()
        GooglePublicProfile(
            email = userInfoResponse.email,
            names = userInfoResponse.name,
            profilePictureUrl = userInfoResponse.picture,
        ).also {
            logger.debug("Google verification succeeded for {}", it)
        }
    }

    /*
     * API:
     * https://developers.google.com/identity/protocols/oauth2/web-server#exchange-authorization-code
     */
    private suspend fun exchangeAuthCodeForTokens(
        code: GoogleAuthorizationCode
    ): Either<String, GoogleTokenResponse> = catch({
        either {
            val response = httpClient.post(
                "https://oauth2.googleapis.com/token"
            ) {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "code" to code.value,
                        "client_id" to config.googleOAuth.clientId,
                        "client_secret" to config.googleOAuth.clientSecret,
                        "redirect_uri" to "http://localhost:8081/auth/google/callback",
                        "grant_type" to "authorization_code"
                    )
                )
            }
            ensure(response.status.isSuccess()) {
                "Verify Google authorization code: status - ${response.status}"
            }
            response.body<GoogleTokenResponse>()
        }
    }) {
        Either.Left("Failed to verify Google authorization code: ${code.value}")
    }

    private suspend fun fetchUserInfo(
        accessToken: String
    ): Either<String, GoogleUserInfoResponse> = catch({
        either {
            val response = httpClient.get("https://www.googleapis.com/oauth2/v3/userinfo") {
                header(HttpHeaders.Authorization, "Bearer $accessToken")
            }
            ensure(response.status.isSuccess()) {
                "Fetch Google user info status code: ${response.status}"
            }
            response.body<GoogleUserInfoResponse>()
        }
    }) { e ->
        Either.Left("Fetch Google user info because $e")
    }

    @Serializable
    data class GoogleUserInfoResponse(
        val email: String,
        val name: String?,
        val picture: String?,
    )

    @Serializable
    data class GoogleTokenResponse(
        @SerialName("access_token")
        val accessToken: String,
    )
}

data class GooglePublicProfile(
    val email: String,
    val names: String?,
    val profilePictureUrl: String?,
)

@JvmInline
value class GoogleAuthorizationCode(val value: String)