package ivy.learn.domain.model

import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.lesson.LessonProgressDto

data class LessonProgress(
    val userId: UserId,
    val courseId: CourseId,
    val lessonId: LessonId,
    val state: LessonProgressDto,
)