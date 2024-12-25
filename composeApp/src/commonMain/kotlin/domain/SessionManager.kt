package domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import data.storage.LocalStorage
import ivy.model.auth.SessionToken

class SessionManager(
  private val localStorage: LocalStorage,
) {
  private var cachedSession: Session? = null

  suspend fun authenticate(token: SessionToken) {
    localStorage.putString(KET_SESSION_TOKEN, token.value)
    cachedSession = Session.LoggedIn(token)
  }

  suspend fun getSessionToken(): Either<String, SessionToken> = either {
    val session = getSession()
    ensure(session is Session.LoggedIn) {
      "No session found. Please login"
    }
    session.sessionToken
  }

  suspend fun getSession(): Session {
    cachedSession?.let { return it }

    val session = loggedInSessionOrNull()
      ?: anonymousSessionOrNull()
    cachedSession = session
    return session ?: Session.LoggedOut
  }

  private suspend fun loggedInSessionOrNull(): Session.LoggedIn? {
    return localStorage.getString(KET_SESSION_TOKEN)
      ?.let { token ->
        Session.LoggedIn(SessionToken(token))
      }
  }

  private suspend fun anonymousSessionOrNull(): Session.Anonymous? {
    return if (localStorage.getBoolean(KEY_IS_ANONYMOUS_SESSION) == true) {
      Session.Anonymous
    } else {
      null
    }
  }

  suspend fun logout() {
    cachedSession = Session.LoggedOut
    localStorage.remove(KET_SESSION_TOKEN)
  }

  companion object {
    const val KET_SESSION_TOKEN = "sessionToken"
    const val KEY_IS_ANONYMOUS_SESSION = "isAnonymousSession"
  }
}

sealed interface Session {
  data class LoggedIn(val sessionToken: SessionToken) : Session
  data object Anonymous : Session
  data object LoggedOut : Session
}