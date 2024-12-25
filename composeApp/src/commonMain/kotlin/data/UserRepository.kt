package data

import arrow.core.Either
import arrow.core.raise.either
import domain.SessionManager
import ivy.data.source.UserDataSource
import kotlinx.coroutines.withContext
import util.DispatchersProvider
import util.Logger

class UserRepository(
    private val dispatches: DispatchersProvider,
    private val sessionManager: SessionManager,
    private val userDataSource: UserDataSource,
    private val logger: Logger,
) {
    suspend fun deleteUserData(): Either<String, Unit> = withContext(dispatches.io) {
        either {
            val session = sessionManager.getSessionToken().bind()
            logger.info("Initiating delete user data request...")
            userDataSource.deleteUserData(session).bind()
        }
    }
}