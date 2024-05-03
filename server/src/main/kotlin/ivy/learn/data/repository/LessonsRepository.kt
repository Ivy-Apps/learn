package ivy.learn.data.repository

import arrow.core.Either
import ivy.learn.data.source.LessonDataSource
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsRepository(
    private val lessonDataSource: LessonDataSource
) {
    suspend fun fetchLesson(course: CourseId, lesson: LessonId): Either<String, Lesson> =
        lessonDataSource.fetchLessonById(course, lesson)
}