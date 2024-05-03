package ivy.data.source.model

import ivy.model.Course
import ivy.model.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    val course: Course,
    val lessons: List<Lesson>
)