package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class IntroLearnMoreClicksKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = metricsDistinctCount(metricName = "intro__click_learn_more")
    KpiDto(
      name = "# of unique intro learn more clicks",
      value = count.toString(),
    )
  }
}