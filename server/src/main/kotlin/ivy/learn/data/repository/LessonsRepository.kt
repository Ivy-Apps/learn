package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import ivy.learn.data.source.LessonDataSource
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsRepository(
    private val lessonDataSource: LessonDataSource
) {
    suspend fun fetchLessonById(id: LessonId): Either<String, Lesson> = catch({
        Either.Right(lessonDataSource.fetchLessonById(id))
    }, {
        Either.Left("Failed to fetch '${id.value}' lesson: $it}")
    })
}