package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.transaction

class AvgLessonViewsPerUserKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    var sum = 0L
    var count = 0
    exec(
      stmt = """
SELECT COUNT(*) FROM analytics 
    WHERE event = ? 
    GROUP by user_id 
      """,
      args = listOf(
        AnalyticsTable.event.columnType to "lesson__view",
      ),
      explicitStatementType = StatementType.SELECT,
      transform = { rs ->
        while (rs.next()) {
          sum += rs.getLong(1)
          count++
        }
      }
    )
    KpiDto(
      name = "Avg # of lesson views per user",
      text = (sum / count.toDouble()).toString(),
    )
  }
}