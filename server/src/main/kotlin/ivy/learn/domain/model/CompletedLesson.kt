package ivy.learn.domain.model

import ivy.learn.CourseId
import ivy.learn.LessonId
import kotlinx.datetime.Instant

data class CompletedLesson(
    val userId: UserId,
    val courseId: CourseId,
    val lessonId: LessonId,
    val time: Instant,
)