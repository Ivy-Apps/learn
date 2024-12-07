package data.lesson

import arrow.core.Either
import data.lesson.mapper.LessonMapper
import domain.model.LessonWithProgress
import ivy.data.source.LessonDataSource
import ivy.model.CourseId
import ivy.model.LessonId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class LessonRepositoryImpl(
    private val dispatchers: DispatchersProvider,
    private val datasource: LessonDataSource,
    private val mapper: LessonMapper,
) : LessonRepository {

    override suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonWithProgress> = withContext(dispatchers.io) {
        datasource.fetchLesson(course, lesson)
            .map {
                with(mapper) {
                    it.toDomain()
                }
            }
    }
}

interface LessonRepository {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonWithProgress>
}