package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.model.analytics.MetricDto

class MetricsDataSource(
  private val httpClient: HttpClient,
  private val urlProvider: ServerUrlProvider,
) {
  suspend fun logMetric(
    metric: MetricDto
  ): Either<String, Unit> = catch({
    either {
      val response = httpClient.post(
        "${urlProvider.serverUrl}/metrics/log"
      ) {
        contentType(ContentType.Application.Json)
        setBody(metric)
      }
      ensure(response.status.isSuccess()) {
        "Log metrics status - ${response.status}"
      }
    }
  }) { e ->
    Either.Left("Log metrics error: $e")
  }
}