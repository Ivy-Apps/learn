package ivy.learn.domain.analytics

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import ivy.learn.domain.analytics.kpi.ratioPercentFormatted
import ivy.learn.domain.analytics.kpi.totalDistinctCount
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.transaction

class KpiFunnel {
  fun compute(): List<KpiDto> = transaction {
    // All counts should be distinct by user_id/client_id
    val introViews = totalDistinctCount(event = "intro__view")
    val enterApp = totalDistinctCount(event = "account__create") +
        totalDistinctCount(event = "intro__click_learn_more")
    val viewCourse = totalDistinctCount(event = "course__view")
    val viewLesson = totalDistinctCount(event = "lesson__view")
    val completeLesson = totalDistinctCount("lesson__complete")
    val usersCompletedAtLeastTwoLessons = analyticsEventDistinctUsersCountHavingAtLeast(
      event = "lesson__complete",
      minEventCount = 2
    )

    listOf(
      KpiDto(
        name = "Open website",
        value = introViews.toString()
      ),
      KpiDto(
        name = "Enter app",
        value = "$enterApp (${ratioPercentFormatted(enterApp, introViews)})"
      ),
      KpiDto(
        name = "View course",
        value = "$viewCourse (${ratioPercentFormatted(viewCourse, enterApp)})"
      ),
      KpiDto(
        name = "View lesson",
        value = "$viewLesson (${ratioPercentFormatted(viewLesson, viewCourse)})"
      ),
      KpiDto(
        name = "Complete lesson",
        value = "$completeLesson (${ratioPercentFormatted(completeLesson, viewLesson)})"
      ),
      KpiDto(
        name = "Complete 2 lesson",
        value = "$usersCompletedAtLeastTwoLessons " +
            "(${ratioPercentFormatted(usersCompletedAtLeastTwoLessons, completeLesson)})"
      ),
    )
  }

  private fun Transaction.analyticsEventDistinctUsersCountHavingAtLeast(
    event: String,
    minEventCount: Int
  ): Long {
    var usersCount = 0L
    exec(
      stmt = """
SELECT count(*) FROM analytics 
    WHERE event = ? 
    GROUP by user_id 
    HAVING count(*) >= ?
""",
      args = listOf(
        AnalyticsTable.event.columnType to event,
        AnalyticsTable.event.count().columnType to minEventCount,
      ),
      explicitStatementType = StatementType.SELECT,
      transform = { rs ->
        while (rs.next()) {
          usersCount++
        }
      }
    )
    return usersCount
  }
}