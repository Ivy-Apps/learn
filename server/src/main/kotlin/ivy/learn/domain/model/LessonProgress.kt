package ivy.learn.domain.model

import ivy.learn.CourseId
import ivy.learn.Lesson.LessonProgressDto
import ivy.learn.LessonId

data class LessonProgress(
    val userId: UserId,
    val courseId: CourseId,
    val lessonId: LessonId,
    val state: LessonProgressDto,
)