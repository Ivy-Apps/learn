package ivy.model.lesson

import ivy.model.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class LessonResponse(
    val lesson: Lesson,
    val progress: LessonProgressDto?,
)