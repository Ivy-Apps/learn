package ivy.learn.domain.analytics

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.util.logging.*
import ivy.learn.data.repository.AnalyticsRepository
import ivy.learn.domain.model.AnalyticsEvent
import ivy.learn.domain.model.UserId
import ivy.learn.util.TimeProvider
import java.util.*

class Analytics(
  private val repository: AnalyticsRepository,
  private val timeProvider: TimeProvider,
  private val logger: Logger,
) {
  fun log(
    userId: UserId,
    eventName: String,
    params: Map<String, String>? = null,
  ): Either<String, AnalyticsEvent> = either {
    ensure(eventName.isNotBlank()) { "Blank event name" }
    val event = AnalyticsEvent(
      id = UUID.randomUUID(),
      userId = userId,
      eventName = eventName,
      time = timeProvider.instantNow(),
      params = params,
    )
    repository.insert(setOf(event)).bind()
    event
  }.onLeft { errMsg ->
    logger.error("[SERVER ANALYTICS] Failed to log event '$eventName' because $errMsg")
  }.onRight { event ->
    logger.info("[SERVER ANALYTICS] Logged $event")
  }
}