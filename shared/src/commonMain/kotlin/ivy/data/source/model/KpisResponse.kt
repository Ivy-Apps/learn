package ivy.data.source.model

import kotlinx.serialization.Serializable

@Serializable
data class KpisResponse(
  val kpis: List<KpiDto>,
)

@Serializable
data class KpiDto(
  val name: String,
  val text: String,
)