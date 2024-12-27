package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

abstract class TopItemDistinctUserIdEventCountKpi : Kpi {
  abstract val metricName: String
  abstract val eventName: String
  abstract fun itemId(params: Map<String, String>): String

  override suspend fun compute(): KpiDto = transaction {
    val textBuilder = StringBuilder()
    analyticsDistinctUserIdCountGroupedByParams(
      eventName = eventName,
      onProcess = { params, count ->
        textBuilder.append(
          "${itemId(params)}: $count\n"
        )
      }
    )
    KpiDto(
      name = metricName,
      value = textBuilder.toString(),
    )
  }
}