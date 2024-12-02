package ivy.data.source

import IvyConstants
import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.model.auth.SessionToken

class UserDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun deleteUserData(
        sessionToken: SessionToken,
    ): Either<String, Unit> = catch({
        either {
            val response = httpClient.delete(
                "${urlProvider.serverUrl}/user/delete-data"
            ) {
                header(IvyConstants.HEADER_SESSION_TOKEN, sessionToken.value)
            }
            ensure(response.status.isSuccess()) {
                "Failed -  ${response.status.value} status"
            }
        }
    }) { e ->
        Either.Left("Unexpected error: $e")
    }

}