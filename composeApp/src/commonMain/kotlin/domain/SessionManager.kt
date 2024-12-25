package domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import data.storage.LocalStorage
import ivy.model.auth.SessionToken

class SessionManager(
  private val localStorage: LocalStorage,
) {
  private var session: Session? = null

  suspend fun authenticate(token: SessionToken) {
    localStorage.putString(SESSION_TOKEN_KEY, token.value)
    session = Session.LoggedIn(token)
  }

  suspend fun getSessionToken(): Either<String, SessionToken> = either {
    val session = getSessionTokenOrNull()
    ensureNotNull(session) {
      "No session found. Please login"
    }
    session
  }

  suspend fun isLoggedIn(): Boolean = getSessionTokenOrNull() != null

  suspend fun getSessionTokenOrNull(): SessionToken? {
    return cachedLoggedSessionOrNull()?.sessionToken ?: localStorage.getString(SESSION_TOKEN_KEY)
      ?.let(::SessionToken)
      ?.also { token ->
        session = Session.LoggedIn(sessionToken = token)
      }
  }

  private fun cachedLoggedSessionOrNull(): Session.LoggedIn? {
    return session as? Session.LoggedIn
  }

  suspend fun logout() {
    session = Session.LoggedOut
    localStorage.remove(SESSION_TOKEN_KEY)
  }

  companion object {
    const val SESSION_TOKEN_KEY = "sessionToken"
    const val IS_LOGGED_OUT_SESSION_KEY = "isLoggedOutSession"
  }
}

sealed interface Session {
  data class LoggedIn(val sessionToken: SessionToken) : Session
  data object Anonymous : Session
  data object LoggedOut : Session
}