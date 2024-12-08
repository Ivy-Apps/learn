package ivy.model.course

import ivy.model.LessonId
import kotlinx.serialization.Serializable

@Serializable
data class CourseProgressRequest(
    val completedLesson: LessonId,
)