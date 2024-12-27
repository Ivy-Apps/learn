package ivy.learn.domain.analytics.kpi

import ivy.model.analytics.AnalyticsParams

class TopLessonsByDistinctCompletionsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Lessons by distinct user_id completions"
  override val eventName = "lesson__complete"
  override fun itemLine(params: Map<String, String>): String {
    return "${params[AnalyticsParams.courseId]}/${params[AnalyticsParams.lessonId]}"
  }
}