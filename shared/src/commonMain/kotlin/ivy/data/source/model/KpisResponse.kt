package ivy.data.source.model

import kotlinx.serialization.Serializable

@Serializable
data class KpisResponse(
  val funnel: List<KpiDto>,
  val kpis: List<KpiDto>,
)

@Serializable
data class KpiDto(
  val name: String,
  val value: String,
)