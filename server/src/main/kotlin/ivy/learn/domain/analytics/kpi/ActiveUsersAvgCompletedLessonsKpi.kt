package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.transaction

class ActiveUsersAvgCompletedLessonsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    var sum = 0L
    var count = 0
    exec(
      stmt = """
SELECT COUNT(*) FROM analytics 
    WHERE event = ? 
    GROUP by user_id 
    HAVING count(*) >= ?       
      """,
      args = listOf(
        AnalyticsTable.event.columnType to "lesson__complete",
        AnalyticsTable.userId.count().columnType to 1L,
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
      name = "Avg # of completed lessons per active user",
      value = (sum / count.toDouble()).toString(),
    )
  }
}