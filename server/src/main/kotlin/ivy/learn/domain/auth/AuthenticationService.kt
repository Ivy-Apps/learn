package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.either
import ivy.learn.data.database.tables.Analytics
import ivy.learn.domain.auth.model.SessionToken
import org.jetbrains.exposed.sql.transactions.transaction

class AuthenticationService(
    private val oauthUseCase: GoogleOAuthUseCase,
) {
    suspend fun authenticate(
        authCode: GoogleAuthorizationCode
    ): Either<String, SessionToken> = either {
        val profile = oauthUseCase.verify(authCode).bind()
        transaction {
            Analytics.new {

            }
        }
        TODO()
    }
}