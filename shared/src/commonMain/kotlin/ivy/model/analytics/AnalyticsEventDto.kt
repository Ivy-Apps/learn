package ivy.model.analytics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class AnalyticsEventDto(
    val eventName: String,
    val time: Instant,
    val params: Map<String, String>?,
)