package ivy.learn.api.common

import io.ktor.server.routing.*
import ivy.data.source.model.KpiResponse
import ivy.learn.domain.analytics.KpiService

class KPIsApi(
  private val service: KpiService,
) : Api {
  override fun Routing.endpoints() {
    kpis()
  }

  private fun Routing.kpis() {
    getEndpointAuthenticated<KpiResponse>("/kpis") { _, sessionToken ->
      service.computeKpis(sessionToken).bind()
    }
  }
}