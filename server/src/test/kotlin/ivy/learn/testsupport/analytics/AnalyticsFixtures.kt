package ivy.learn.testsupport.analytics

import ivy.learn.testsupport.timeNow
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest
import kotlinx.datetime.Instant

object AnalyticsFixtures {
    fun eventsRequest(vararg events: AnalyticsEventDto) = LogAnalyticsEventsRequest(
        events = events.toSet()
    )

    fun eventDto(
        eventName: String,
        params: Map<String, String> = emptyMap(),
        time: Instant = timeNow(),
    ) = AnalyticsEventDto(
        eventName = eventName,
        time = time,
        params = params
    )
}