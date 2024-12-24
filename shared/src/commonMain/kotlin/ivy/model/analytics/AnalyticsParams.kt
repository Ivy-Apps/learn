package ivy.model.analytics

import ivy.learn.CourseId
import ivy.learn.LessonId

object AnalyticsParams {
  val courseId = "courseId"
  val courseName = "courseName"
  val lessonId = "lessonId"
  val lessonName = "lessonName"
  val questionId = "questionId"
  val itemId = "itemId"
  val answers = "answers"
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.courseId(id: CourseId) {
  param(AnalyticsParams.courseId, id.value)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.courseName(name: String) {
  param(AnalyticsParams.courseName, name)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.lessonId(id: LessonId) {
  param(AnalyticsParams.lessonId, id.value)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.lessonName(name: String) {
  param(AnalyticsParams.lessonName, name)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.questionId(id: String) {
  param(AnalyticsParams.questionId, id)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.itemId(id: String) {
  param(AnalyticsParams.itemId, id)
}

@AnalyticsParamsDsl
fun AnalyticsParamsScope.answers(answersText: String) {
  param(AnalyticsParams.answers, answersText)
}