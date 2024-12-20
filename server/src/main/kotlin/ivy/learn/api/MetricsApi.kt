package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.postEndpoint
import ivy.model.analytics.MetricsEventDto

class MetricsApi(
) : Api {
  override fun Routing.endpoints() {
    postEvents()
  }

  private fun Routing.postEvents() {
    postEndpoint<MetricsEventDto, Unit>(
      "metrics/event"
    ) { _, request ->
      TODO()
    }
  }
}