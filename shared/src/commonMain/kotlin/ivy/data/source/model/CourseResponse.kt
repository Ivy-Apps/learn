package ivy.data.source.model

import ivy.learn.Course
import ivy.learn.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
  val course: Course,
  val lessons: List<Lesson>
)