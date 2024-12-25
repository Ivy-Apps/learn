package domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import data.storage.LocalStorage
import ivy.model.auth.SessionToken

class SessionManager(
    private val localStorage: LocalStorage,
) {
    private var sessionToken: SessionToken? = null

    suspend fun authenticate(token: SessionToken) {
        localStorage.putString(SESSION_TOKEN_KEY, token.value)
        sessionToken = token
    }

    suspend fun getSession(): Either<String, SessionToken> = either {
        val session = getSessionOrNull()
        ensureNotNull(session) {
            "No session found. Please login"
        }
        session
    }

    suspend fun isLoggedIn(): Boolean = getSessionOrNull() != null

    suspend fun getSessionOrNull(): SessionToken? {
        return sessionToken ?: localStorage.getString(SESSION_TOKEN_KEY)
            ?.let(::SessionToken)
            ?.also {
                sessionToken = it
            }
    }

    suspend fun logout() {
        sessionToken = null
        localStorage.remove(SESSION_TOKEN_KEY)
    }

    companion object {
        const val SESSION_TOKEN_KEY = "sessionToken"
    }
}