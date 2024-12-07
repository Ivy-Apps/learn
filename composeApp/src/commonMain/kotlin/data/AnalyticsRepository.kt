package data

import arrow.core.Either
import arrow.core.raise.either
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
            val session = sessionManager.getSession().bind()
            dataSource.logEvents(
                session = session,
                request = LogAnalyticsEventsRequest(
                    events = events,
                )
            ).bind()
        }
    }
}