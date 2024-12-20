package ivy.learn.domain.auth

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.auth.SessionRepository
import ivy.learn.data.repository.auth.UserRepository
import ivy.learn.domain.analytics.Analytics
import ivy.learn.domain.model.Session
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import ivy.learn.util.Crypto
import ivy.learn.util.TimeProvider
import ivy.model.auth.SessionToken
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import java.util.*

class AuthService(
  private val oauthUseCase: GoogleOAuthUseCase,
  private val userRepository: UserRepository,
  private val sessionRepository: SessionRepository,
  private val crypto: Crypto,
  private val timeProvider: TimeProvider,
  private val analytics: Analytics,
) {
  companion object {
    const val SESSION_EXPIRATION_DAYS = 90
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
      analytics.log(
        userId = user.id,
        eventName = "account__create"
      )
    } else {
      analytics.log(
        userId = user.id,
        eventName = "account__login"
      )
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

  suspend fun getUser(
    sessionToken: SessionToken
  ): Either<ServerError, User> = either {
    val session = sessionRepository.findSessionByToken(sessionToken)
      .mapLeft(ServerError::Unknown)
      .bind()
    ensureNotNull(session) {
      ServerError.Unauthorized
    }
    ensure(session.expiresAt > timeProvider.instantNow()) {
      ServerError.SessionExpired
    }

    val user = userRepository.findUserById(session.userId)
      .mapLeft(ServerError::Unknown)
      .bind()
    ensureNotNull(user) {
      ServerError.Unauthorized
    }
    user
  }
}

data class Auth(
  val user: User,
  val session: Session,
)