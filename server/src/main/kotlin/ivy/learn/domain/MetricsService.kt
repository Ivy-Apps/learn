package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.MetricsRepository
import ivy.learn.domain.model.ClientId
import ivy.learn.domain.model.Metric
import ivy.model.analytics.MetricDto
import java.util.*

class MetricsService(
  private val metricsRepository: MetricsRepository,
) {
  fun logMetric(metricDto: MetricDto): Either<ServerError, Unit> = either {
    val metric = metricDto.toDomain()
      .mapLeft(ServerError::BadRequest)
      .bind()
    metricsRepository.insert(metric)
      .mapLeft(ServerError::Unknown)
      .bind()
  }

  private fun MetricDto.toDomain(): Either<String, Metric> = either {
    ensure(name.isNotBlank()) { "Blank name" }
    ensure(clientId.isNotBlank()) { "Blank clientId" }

    Metric(
      id = UUID.randomUUID(),
      clientId = ClientId(clientId),
      name = name,
      time = time,
      params = params,
    )
  }
}