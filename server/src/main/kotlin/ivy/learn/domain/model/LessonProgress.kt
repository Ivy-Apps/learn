package ivy.learn.domain.model

import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.model.lesson.LessonProgressDto

data class LessonProgress(
    val userId: UserId,
    val courseId: CourseId,
    val lessonId: LessonId,
    val state: LessonProgressDto,
)