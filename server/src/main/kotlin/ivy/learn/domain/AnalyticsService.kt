package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import ivy.learn.data.repository.AnalyticsRepository
import ivy.learn.domain.model.AnalyticsEvent
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest
import java.util.*

class AnalyticsService(
    private val repository: AnalyticsRepository,
) {
    fun logEvents(
        user: User,
        request: LogAnalyticsEventsRequest,
    ): Either<String, Collection<AnalyticsEvent>> = either {
        val events = request.events.mapNotNull {
            AnalyticsEvent(
                id =,
                userId = UserId(value =),
                eventName = "",
                time =,
                params = mapOf()
            )
        }

        TODO()
    }

    private fun AnalyticsEventDto.toDomain(
        user: User
    ): Either<String, AnalyticsEvent> = either {
        ensure(eventName.isNotBlank()) { "Blank event name" }
        ensure(Regex("^[a-z1-9_]+$").matches(eventName)) {
            "Invalid event name: must only contain lowercase letters, numbers, and underscores"
        }
        AnalyticsEvent(
            id = UUID.randomUUID(),
            eventName = eventName,
            time = time,
            userId = user.id,
            params = params,
        )
    }

    data class LogEventsResult(

    )
}