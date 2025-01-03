package ivy.learn.domain.analytics.kpi

import ivy.model.analytics.AnalyticsParams

class TopCoursesByDistinctViewsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Courses by most user views"
  override val eventName = "course__view"
  override fun itemId(params: Map<String, String>): String {
    return params[AnalyticsParams.courseId]!!
  }
}