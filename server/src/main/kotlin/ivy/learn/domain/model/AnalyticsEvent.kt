package ivy.learn.domain.model

import kotlinx.datetime.Instant
import java.util.*

data class AnalyticsEvent(
    val id: UUID,
    val userId: UserId,
    val eventName: String,
    val time: Instant,
    val params: Map<String, String>?,
)