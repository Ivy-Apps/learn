package ivy.model.analytics

import kotlinx.serialization.Serializable

@Serializable
data class AnalyticsEventsRequest(
    val events: Set<AnalyticsEventDto>
)