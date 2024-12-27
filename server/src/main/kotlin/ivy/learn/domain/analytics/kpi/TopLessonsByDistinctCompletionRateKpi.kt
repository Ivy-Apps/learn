package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import org.jetbrains.exposed.sql.transactions.transaction

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
      name = "Top Lessons by most user completion rate %",
      value = lessonViews
        .filter { (_, views) ->
          views > 0
        }
        .map { (lessonId, views) ->
          val completions = lessonCompletions[lessonId] ?: 0
          val ratioPercentFormatted = ratioPercentFormatted(completions, views)
          val formattedText = "$lessonId: $ratioPercentFormatted ($completions completions / $views views)"
          ratioPercent(views, completions) to formattedText
        }.sortedByDescending { (completionRatio, _) ->
          completionRatio
        }.joinToString(separator = "\n") { (_, text) ->
          text
        }
    )
  }
}