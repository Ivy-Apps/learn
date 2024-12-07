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
import ivy.model.analytics.LogAnalyticsEventsRequest
import ivy.model.auth.SessionToken

class AnalyticsDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun logEvents(
        session: SessionToken,
        request: LogAnalyticsEventsRequest
    ): Either<String, Unit> = catch({
        either {
            val response = httpClient.post(
                "${urlProvider.serverUrl}/analytics/events"
            ) {
                header(IvyConstants.HEADER_SESSION_TOKEN, session.value)
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            ensure(response.status.isSuccess()) {
                "Log events status - ${response.status}"
            }
        }
    }) { e ->
        Either.Left("Log events error: $e")
    }
}