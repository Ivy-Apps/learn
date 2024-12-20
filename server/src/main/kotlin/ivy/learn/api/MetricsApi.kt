package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.postEndpoint
import ivy.learn.domain.MetricsService
import ivy.model.analytics.MetricDto

class MetricsApi(
  private val service: MetricsService,
) : Api {
  override fun Routing.endpoints() {
    postEvents()
  }

  private fun Routing.postEvents() {
    postEndpoint<MetricDto, Unit>(
      "metrics/event"
    ) { metricBody, _ ->
      service.logMetric(metricBody).bind()
    }
  }
}