package ivy.learn.domain.analytics.kpi

class TopLessonsByDistinctCompletionsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Lessons by distinct user_id completions"
  override val eventName = "lesson__complete"
  override fun itemId(params: Map<String, String>): String {
    return lessonKpiId(params)
  }
}