package data

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import domain.SessionManager
import ivy.data.source.AnalyticsDataSource
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class AnalyticsRepository(
    private val sessionManager: SessionManager,
    private val dispatchers: DispatchersProvider,
    private val dataSource: AnalyticsDataSource
) {
    suspend fun logEvent(
        events: Set<AnalyticsEventDto>,
    ): Either<String, Unit> = either {
        withContext(dispatchers.io) {
            val session = sessionManager.getSession()
            ensureNotNull(session) {
                "No session found. Please login"
            }
            dataSource.logEvents(
                sessionToken = session,
                request = LogAnalyticsEventsRequest(
                    events = events,
                )
            ).bind()
        }
    }
}