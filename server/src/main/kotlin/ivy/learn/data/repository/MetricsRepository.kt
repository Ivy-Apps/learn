package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import ivy.learn.data.database.tables.MetricsTable
import ivy.learn.domain.model.Metric
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class MetricsRepository {
  fun insert(
    metric: Metric,
  ): Either<String, Metric> = catch({
    transaction {
      MetricsTable.insert {
        it[id] = metric.id
        it[clientId] = metric.clientId.value
        it[name] = metric.name
        it[time] = metric.time
        it[params] = metric.params
      }
    }
    Either.Right(metric)
  }) { e ->
    Either.Left("Failed to insert $metric because $e")
  }
}