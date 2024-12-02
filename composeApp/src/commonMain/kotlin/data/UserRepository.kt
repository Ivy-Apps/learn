package data

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import domain.SessionManager
import ivy.data.source.UserDataSource
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class UserRepository(
    private val dispatches: DispatchersProvider,
    private val sessionManager: SessionManager,
    private val userDataSource: UserDataSource,
) {
    suspend fun deleteUserData(): Either<String, Unit> = withContext(dispatches.io) {
        either {
            val session = sessionManager.getSession()
            ensureNotNull(session) {
                "No user session"
            }
            userDataSource.deleteUserData(session).bind()
        }
    }
}