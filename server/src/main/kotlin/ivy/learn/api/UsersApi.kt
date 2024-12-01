package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.deleteEndpointAuthenticated
import ivy.learn.domain.auth.AuthenticationService

class UsersApi(
    private val authService: AuthenticationService,
) : Api {
    override fun Routing.endpoints() {
        deleteUserData()
    }

    private fun Routing.deleteUserData() {
        deleteEndpointAuthenticated("/user/delete-data") { _, sessionToken ->

        }
    }
}