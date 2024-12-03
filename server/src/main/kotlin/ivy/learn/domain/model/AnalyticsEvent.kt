package ivy.learn.domain.model

import kotlinx.datetime.Instant
import java.util.*

data class AnalyticsEvent(
    val id: UUID,
    val name: String,
    val time: Instant,
    val userId: UserId,
)