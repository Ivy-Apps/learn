package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.DecimalFormat

class TopLessonsByDistinctCompletionRateKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val lessonViews = mutableMapOf<String, Long>()
    val lessonCompletions = mutableMapOf<String, Long>()
    analyticsDistinctUserIdCountGroupedByParams(
      eventName = "lesson__view",
      onProcess = { params, count ->
        lessonViews[lessonKpiId(params)] = count
      }
    )
    analyticsDistinctUserIdCountGroupedByParams(
      eventName = "lesson__complete",
      onProcess = { params, count ->
        lessonCompletions[lessonKpiId(params)] = count
      }
    )
    KpiDto(
      name = "Top Lessons by distinct user_id completion rate %",
      value = buildString {
        lessonViews.forEach { (lessonId, views) ->
          if (views > 0) {
            val completions = lessonCompletions[lessonId] ?: 0
            val percentageFormatter = DecimalFormat("#.00")
            val ratio = (completions / views) * 100
            append("$lessonId: ${percentageFormatter.format(ratio)}% ($completions completions / $views views)\n")
          }
        }
      }
    )
  }
}