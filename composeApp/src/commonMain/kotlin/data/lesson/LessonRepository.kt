package data.lesson

import arrow.core.Either
import data.lesson.mapper.LessonMapper
import domain.model.LessonProgress
import domain.model.LessonWithProgress
import ivy.data.source.LessonDataSource
import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.lesson.LessonProgressDto
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

    override suspend fun saveLessonProgress(
        course: CourseId,
        lesson: LessonId,
        progress: LessonProgress
    ): Either<String, Unit> = withContext(dispatchers.io) {
        datasource.saveProgress(
            course = course,
            lesson = lesson,
            progress = LessonProgressDto(
                selectedAnswers = progress.selectedAnswers,
                openAnswersInput = progress.openAnswersInput,
                chosen = progress.chosen,
                answered = progress.answered,
                completed = progress.completed,
            )
        )
    }
}

interface LessonRepository {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonWithProgress>

    suspend fun saveLessonProgress(
        course: CourseId,
        lesson: LessonId,
        progress: LessonProgress,
    ): Either<String, Unit>
}