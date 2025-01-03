package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

class CoursesViewKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = totalDistinctCount(event = "course__view")
    KpiDto(
      name = "# of people viewed a course",
      value = count.toString()
    )
  }
}