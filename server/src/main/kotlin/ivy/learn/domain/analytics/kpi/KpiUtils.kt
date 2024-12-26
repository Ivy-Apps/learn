package ivy.learn.domain.analytics.kpi

import ivy.learn.data.database.tables.AnalyticsTable
import ivy.learn.data.database.tables.MetricsTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.countDistinct

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
fun Transaction.analyticsDistinctCount(eventName: String): Long = AnalyticsTable
  .select(AnalyticsTable.userId.countDistinct())
  .where { AnalyticsTable.event eq eventName }
  .single()[AnalyticsTable.userId.countDistinct()]