package ivy.model.analytics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class MetricDto(
  val clientId: String,
  val name: String,
  val time: Instant,
  val params: Map<String, String>?,
)