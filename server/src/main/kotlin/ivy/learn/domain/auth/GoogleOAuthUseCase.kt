package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.learn.config.ServerConfiguration
import ivy.learn.util.Base64Util
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class GoogleOAuthUseCase(
    private val config: ServerConfiguration,
    private val httpClient: HttpClient,
    private val base64: Base64Util,
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
                        "redirect_uri" to "http://localhost:8081/auth/google/callback",
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
        val idTokenPayload = decodeIdTokenPayload(idToken)
            .mapLeft { errMsg ->
                "Google ID token decode: $errMsg; idToken = $idToken"
            }
            .bind()
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

    private fun decodeIdTokenPayload(
        idToken: String
    ): Either<String, Map<String, String>> = either {
        val payloadBase64 = catch({
            idToken.split(".")[1].right()
        }) { e ->
            "Split on '.' for \"$idToken\" because $e".left()
        }.bind()
        val payloadText = base64.decode(payloadBase64).bind()
        val payload = catch({
            Json.decodeFromString<Map<String, String>>(payloadText).right()
        }) { e ->
            "JSON decode of '$payloadText' because $e".left()
        }.bind()
        payload
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