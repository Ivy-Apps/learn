package data

import arrow.core.Either
import ivy.data.source.LessonDataSource
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class LessonRepository(
    private val dispatchers: DispatchersProvider,
    private val datasource: LessonDataSource,
) {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Lesson> = withContext(dispatchers.io) {
        datasource.fetchLesson(course, lesson)
    }
}