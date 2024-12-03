package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import ivy.learn.data.repository.AnalyticsRepository
import ivy.learn.domain.model.AnalyticsEvent
import ivy.learn.domain.model.User
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.AnalyticsEventsRequest
import java.util.*

class AnalyticsService(
    private val repository: AnalyticsRepository,
) {
    fun logEvents(
        user: User,
        request: AnalyticsEventsRequest,
    ): Either<String, Collection<AnalyticsEvent>> = either {
        val events = request.events.mapNotNull {
            AnalyticsEvent(

            )
        }

        TODO()
    }

    private fun AnalyticsEventDto.toDomain(
        user: User
    ): Either<String, AnalyticsEvent> = either {
        ensure(name.isNotBlank()) { "Blank event name" }
        ensure(Regex("^[a-z1-9_]+$").matches(name)) {
            "Invalid event name: must only contain lowercase letters, numbers, and underscores"
        }
        AnalyticsEvent(
            id = UUID.randomUUID(),
            name = name,
            time = time,
            userId = user.id,
        )
    }

    data class LogEventsResult(

    )
}