package domain

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

    suspend fun getSession(): SessionToken? {
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