package ivy.learn.domain.analytics.kpi

import ivy.model.analytics.AnalyticsParams

class TopLessonsByDistinctViewsKpi : TopItemDistinctUserIdEventCountKpi() {
  override val metricName = "Top Lessons by distinct user_id views"
  override val eventName = "lesson__view"
  override fun itemLine(params: Map<String, String>): String {
    return "${params[AnalyticsParams.courseId]}/${params[AnalyticsParams.lessonId]}"
  }
}