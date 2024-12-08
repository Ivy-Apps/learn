package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.deleteEndpointAuthenticated
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.auth.UserRepository
import ivy.learn.domain.auth.AuthenticationService
import org.slf4j.Logger

class UsersApi(
    private val authService: AuthenticationService,
    private val userRepository: UserRepository,
    private val logger: Logger,
) : Api {
    override fun Routing.endpoints() {
        deleteUserData()
    }

    private fun Routing.deleteUserData() {
        deleteEndpointAuthenticated<Unit>("/user/delete-data") { _, sessionToken ->
            val user = authService.getUser(sessionToken).bind()
            logger.info("User '{}' is requesting account deletion...", user.email)
            userRepository.delete(user.id)
                .mapLeft { ServerError.Unknown(it) }
                .bind()
            logger.info("User data for '{}' deleted", user.email)
        }
    }
}