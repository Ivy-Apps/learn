package ivy.model.analytics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class AnalyticsEventDto(
    val name: String,
    val time: Instant,
)