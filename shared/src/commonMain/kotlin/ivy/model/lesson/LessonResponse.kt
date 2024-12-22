package ivy.model.lesson

import ivy.learn.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class LessonResponse(
    val lesson: Lesson,
    val progress: LessonProgressDto?,
)