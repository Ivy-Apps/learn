package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.either
import ivy.learn.data.repository.auth.SessionRepository
import ivy.learn.data.repository.auth.UserRepository
import ivy.learn.domain.model.Session
import ivy.learn.domain.model.SessionToken
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import ivy.learn.util.Crypto
import ivy.learn.util.TimeProvider
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import java.util.*

class AuthenticationService(
    private val oauthUseCase: GoogleOAuthUseCase,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val crypto: Crypto,
    private val timeProvider: TimeProvider,
) {
    companion object {
        const val SESSION_EXPIRATION_DAYS = 30
    }

    suspend fun authenticate(
        authCode: GoogleAuthorizationCode
    ): Either<String, Auth> = either {
        val googleProfile = oauthUseCase.verify(authCode).bind()
        // 1. Get or create user
        var user = userRepository.findUserByEmail(googleProfile.email).bind()
        if (user == null) {
            user = userRepository.create(
                User(
                    id = UserId(UUID.randomUUID()),
                    email = googleProfile.email,
                    names = googleProfile.names,
                    profilePicture = googleProfile.profilePictureUrl,
                )
            ).bind()
        }

        // 2. Create a new session
        val timeNow = timeProvider.instantNow()
        val session = sessionRepository.create(
            Session(
                token = SessionToken(crypto.generateSecureToken()),
                userId = user.id,
                createdAt = timeNow,
                expiresAt = timeNow.plus(SESSION_EXPIRATION_DAYS, DateTimeUnit.DAY, TimeZone.UTC),
            )
        ).bind()

        Auth(
            user = user,
            session = session,
        )
    }
}

data class Auth(
    val user: User,
    val session: Session,
)