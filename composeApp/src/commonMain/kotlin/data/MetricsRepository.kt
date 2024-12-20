package data

import arrow.core.Either
import arrow.core.raise.either
import ivy.data.source.MetricsDataSource
import ivy.model.analytics.MetricDto
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class MetricsRepository(
  private val dispatchers: DispatchersProvider,
  private val dataSource: MetricsDataSource
) {
  suspend fun log(
    metric: MetricDto,
  ): Either<String, Unit> = either {
    withContext(dispatchers.io) {
      dataSource.logMetric(
        metric = metric,
      ).bind()
    }
  }
}