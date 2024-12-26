package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class IntroViewsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = metricsDistinctCount(metricName = "intro__view")
    KpiDto(
      name = "# of unique intro views",
      text = count.toString()
    )
  }
}