package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class LessonViewsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = analyticsDistinctCount(event = "lesson__view")
    KpiDto(
      name = "# of users viewed a lesson",
      text = count.toString()
    )
  }
}