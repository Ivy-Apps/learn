package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import ivy.learn.config.ServerConfiguration

class GoogleOAuthUseCase(
    private val config: ServerConfiguration,
) {
    suspend fun verify(
        code: GoogleAuthorizationCode
    ): Either<String, GooglePublicProfile> = either {
        ensure(code.value.isNotBlank()) {
            "Google authorization code is blank!"
        }
        val clientId = config.googleOAuth.clientId
        val clientSecret = config.googleOAuth.clientSecret
        TODO()
    }
}

data class GooglePublicProfile(
    val email: String,
    val names: String,
    val profilePictureUrl: String?,
)

@JvmInline
value class GoogleAuthorizationCode(val value: String)