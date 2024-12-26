package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import org.jetbrains.exposed.sql.avg
import org.jetbrains.exposed.sql.countDistinct
import org.jetbrains.exposed.sql.transactions.transaction

class ActiveUsersAvgCompletedLessonsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = AnalyticsTable
      .select(AnalyticsTable.userId.countDistinct().avg())
      .where { AnalyticsTable.event eq "lesson__complete" }
      .groupBy(AnalyticsTable.userId)
      .having {
        AnalyticsTable.userId.countDistinct() greaterEq 1
      }
      .single()[AnalyticsTable.userId.countDistinct().avg()]
    KpiDto(
      name = "# of avg completed lessons per active user",
      text = count?.toLong()?.toString() ?: "null :(",
    )
  }
}