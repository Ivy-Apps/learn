package ivy.learn.domain.model

import kotlinx.datetime.Instant
import java.util.*

data class Metric(
  val id: UUID,
  val clientId: ClientId,
  val name: String,
  val time: Instant,
  val params: Map<String, String>?,
)

@JvmInline
value class ClientId(val value: String)