package ivy.data.source.model

import ivy.learn.Course
import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.learn.Topic
import kotlinx.serialization.Serializable

@Serializable
data class TopicsResponse(
  val topics: List<Topic>,
  val courses: List<Course>,
  val progress: Map<CourseId, Set<LessonId>>,
)
