package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class IntroLearnMoreClicksKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = metricsDistinctCount(metricName = "intro__learn_more_click")
    KpiDto(
      name = "# of unique intro learn more clicks",
      text = count.toString(),
    )
  }
}