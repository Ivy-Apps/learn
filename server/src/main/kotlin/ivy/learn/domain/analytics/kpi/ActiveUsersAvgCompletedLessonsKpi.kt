package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.transactions.transaction

class ActiveUsersAvgCompletedLessonsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    var sum = 0L
    var count = 0
    exec(
      stmt = """
SELECT COUNT(*) FROM analytics 
    WHERE event = 'lesson__complete' 
    GROUP by user_id 
    HAVING count(*) >= 1        
      """,
      args = listOf(
        AnalyticsTable.userId.columnType to "lesson__complete",
        AnalyticsTable.userId.count().columnType to 1L,
      ).take(0),
      explicitStatementType = null,
      transform = { rs ->
        while (rs.next()) {
          sum += rs.getLong(1)
          count++
        }
      }
    )
    KpiDto(
      name = "# of avg completed lessons per active user",
      text = (sum / count.toDouble()).toString(),
    )
  }
}