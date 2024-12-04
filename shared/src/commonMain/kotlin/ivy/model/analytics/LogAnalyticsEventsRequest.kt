package ivy.model.analytics

import kotlinx.serialization.Serializable

@Serializable
data class LogAnalyticsEventsRequest(
    val events: Set<AnalyticsEventDto>
)