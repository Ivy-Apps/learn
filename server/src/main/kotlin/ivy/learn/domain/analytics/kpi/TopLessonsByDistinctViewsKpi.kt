package ivy.learn.domain.analytics.kpi

class TopLessonsByDistinctViewsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Lessons by most user views"
  override val eventName = "lesson__view"
  override fun itemId(params: Map<String, String>): String {
    return lessonKpiId(params)
  }
}