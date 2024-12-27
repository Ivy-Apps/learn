package ivy.learn.domain.analytics.kpi

import ivy.learn.data.database.tables.AnalyticsTable
import ivy.learn.data.database.tables.MetricsTable
import ivy.model.analytics.AnalyticsParams
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.countDistinct
import org.jetbrains.exposed.sql.statements.StatementType

@Suppress("unused")
fun Transaction.totalDistinctCount(event: String): Long {
  return analyticsDistinctCount(event) + metricsDistinctCount(event)
}

@Suppress("unused")
fun Transaction.metricsDistinctCount(metricName: String): Long = MetricsTable
  .select(MetricsTable.clientId.countDistinct())
  .where { MetricsTable.name eq metricName }
  .single()[MetricsTable.clientId.countDistinct()]

@Suppress("unused")
fun Transaction.analyticsDistinctCount(event: String): Long = AnalyticsTable
  .select(AnalyticsTable.userId.countDistinct())
  .where { AnalyticsTable.event eq event }
  .single()[AnalyticsTable.userId.countDistinct()]

@Suppress("unused")
fun Transaction.analyticsDistinctUserIdCountGroupedByParams(
  eventName: String,
  onProcess: (params: Map<String, String>, count: Long) -> Unit,
) {
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
        onProcess(params, count)
      }
    }
  )
}


fun lessonKpiId(params: Map<String, String>): String {
  return "${params[AnalyticsParams.courseId]}/${params[AnalyticsParams.lessonId]}"
}