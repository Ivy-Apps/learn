package ivy.learn.domain.analytics.kpi

class TopLessonsByDistinctCompletionsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Lessons by most user completions"
  override val eventName = "lesson__complete"
  override fun itemId(params: Map<String, String>): String {
    return lessonKpiId(params)
  }
}