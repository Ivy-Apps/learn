package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.util.logging.*
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.AnalyticsRepository
import ivy.learn.domain.model.AnalyticsEvent
import ivy.learn.domain.model.User
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest
import java.util.*

class AnalyticsService(
    private val repository: AnalyticsRepository,
    private val logger: Logger,
) {
    suspend fun logEvents(
        user: User,
        request: LogAnalyticsEventsRequest,
    ): Either<ServerError, Unit> = either {
        val events = request.events
            .mapNotNull { event ->
                event.toDomain(user)
                    .onLeft { errMsg ->
                        logger.error("Invalid analytics event $event because $errMsg")
                    }
                    .getOrNull()
            }.toSet()
        ensure(events.isNotEmpty()) {
            ServerError.BadRequest("No valid events were found.")
        }
        repository.insert(events)
            .mapLeft(ServerError::Unknown)
            .bind()
    }

    private fun AnalyticsEventDto.toDomain(
        user: User
    ): Either<String, AnalyticsEvent> = either {
        ensure(eventName.isNotBlank()) { "Blank event name" }
        ensure(Regex("^[a-z1-9_]+$").matches(eventName)) {
            "Event name: must only contain lowercase letters, numbers, and underscores"
        }
        AnalyticsEvent(
            id = UUID.randomUUID(),
            eventName = eventName,
            time = time,
            userId = user.id,
            params = params,
        )
    }
}