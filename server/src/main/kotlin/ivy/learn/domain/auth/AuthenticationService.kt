package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.either
import ivy.learn.domain.model.Session

class AuthenticationService(
    private val oauthUseCase: GoogleOAuthUseCase,
) {
    suspend fun authenticate(
        authCode: GoogleAuthorizationCode
    ): Either<String, Session> = either {
        val profile = oauthUseCase.verify(authCode).bind()

        TODO()
    }
}