package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.AnalyticsTable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.transaction

abstract class TopItemDistinctUserIdEventCountKpi : Kpi {
  abstract val metricName: String
  abstract val eventName: String
  abstract fun itemLine(params: Map<String, String>): String

  override suspend fun compute(): KpiDto = transaction {
    val textBuilder = StringBuilder()
    exec(
      stmt = """
SELECT params::TEXT, count(DISTINCT user_id) FROM analytics 
    WHERE event = ? 
    GROUP by params::TEXT 
    ORDER BY count(DISTINCT user_id) DESC;       
      """,
      args = listOf(
        AnalyticsTable.event.columnType to eventName,
      ),
      explicitStatementType = StatementType.SELECT,
      transform = { rs ->
        while (rs.next()) {
          val params = Json.decodeFromString<Map<String, String>>(rs.getString(1))
          val count = rs.getLong(2)
          textBuilder.append(
            "${itemLine(params)}: $count\n"
          )
        }
      }
    )
    KpiDto(
      name = metricName,
      value = textBuilder.toString(),
    )
  }
}