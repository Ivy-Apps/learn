package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.MetricsTable
import org.jetbrains.exposed.sql.countDistinct
import org.jetbrains.exposed.sql.transactions.transaction

class IntroViewsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = MetricsTable.select(MetricsTable.clientId.countDistinct())
      .where { MetricsTable.name eq "intro__view" }
      .single()[MetricsTable.clientId.countDistinct()]
    KpiDto(
      name = "# of unique intro view",
      text = count.toString()
    )
  }
}