package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class UsersCompletedLessonKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = analyticsDistinctCount(event = "lesson__complete")
    KpiDto(
      name = "# of users completed a lesson",
      text = count.toString(),
    )
  }
}