package ivy.model.course

import ivy.learn.LessonId
import kotlinx.serialization.Serializable

@Serializable
data class CourseProgressRequest(
    val completedLesson: LessonId,
)