package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.learn.config.ServerConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*

class GoogleOAuthUseCase(
    private val config: ServerConfiguration,
    private val httpClient: HttpClient,
) {

    suspend fun verify(
        authCode: GoogleAuthorizationCode
    ): Either<String, GooglePublicProfile> = either {
        ensure(authCode.value.isNotBlank()) {
            "Google authorization code is blank!"
        }
        val tokenResponse = exchangeAuthCodeForTokens(authCode).bind()
        extractPublicProfile(tokenResponse.idToken).bind()
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
                        "redirect_uri" to "http://localhost:8080/auth/google/callback",
                        "grant_type" to "authorization_code"
                    )
                )
            }
            ensure(response.status.isSuccess()) {
                "Failed to verify Google authorization code: status - ${response.status}"
            }
            response.body<GoogleTokenResponse>()
        }
    }) {
        Either.Left("Failed to verify Google authorization code: ${code.value}")
    }

    private fun extractPublicProfile(idToken: String): Either<String, GooglePublicProfile> = either {
        val idTokenPayload = catch({ decodeJwt(idToken) }) { e ->
            raise("Failed to decode Google JWT idToken '$idToken': $e")
        }
        val audience = idTokenPayload["aud"]
        ensure(audience != config.googleOAuth.clientId) {
            "Google ID token is not intended for our client"
        }

        val email = idTokenPayload["email"]
        ensureNotNull(email) {
            "Google ID token Email is null"
        }
        val name = idTokenPayload["name"]
        val picture = idTokenPayload["picture"]


        GooglePublicProfile(
            email = email,
            names = name,
            profilePictureUrl = picture
        )
    }

    private fun decodeJwt(jwt: String): Map<String, String> {
        val payload = jwt.split(".")[1]
        val decodedBytes = Base64.getUrlDecoder().decode(payload)
        return Json.decodeFromString(decodedBytes.decodeToString())
    }


    @Serializable
    data class GoogleTokenResponse(
        @SerialName("id_token")
        val idToken: String,
    )
}


data class GooglePublicProfile(
    val email: String,
    val names: String?,
    val profilePictureUrl: String?,
)

@JvmInline
value class GoogleAuthorizationCode(val value: String)