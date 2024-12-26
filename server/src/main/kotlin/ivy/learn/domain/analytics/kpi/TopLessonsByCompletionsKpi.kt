package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import ivy.model.analytics.AnalyticsParams
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.transaction

class TopLessonsByCompletionsKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val textBuilder = StringBuilder()
    exec(
      stmt = """
SELECT params::TEXT, count(*) FROM analytics 
    WHERE event = ? 
    GROUP by params::TEXT 
    ORDER BY count(*) DESC;       
      """,
      args = listOf(
        AnalyticsTable.event.columnType to "lesson__complete",
      ),
      explicitStatementType = StatementType.SELECT,
      transform = { rs ->
        while (rs.next()) {
          val params = Json.decodeFromString<Map<String, String>>(rs.getString(1))
          val count = rs.getLong(2)
          if (textBuilder.isNotBlank()) {
            textBuilder.append(", ")
          }
          textBuilder.append(
            "${params[AnalyticsParams.courseId]}/${params[AnalyticsParams.lessonId]}: $count"
          )
        }
      }
    )
    KpiDto(
      name = "Top Lessons by completions",
      text = textBuilder.toString(),
    )
  }
}