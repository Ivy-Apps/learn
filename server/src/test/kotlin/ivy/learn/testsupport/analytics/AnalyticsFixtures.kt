package ivy.learn.testsupport.analytics

import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest

object AnalyticsFixtures {
    fun eventsRequest(vararg events: AnalyticsEventDto) = LogAnalyticsEventsRequest(
        events = events.toSet()
    )
}