package data.lesson

import arrow.core.Either
import arrow.core.raise.either
import data.lesson.mapper.LessonMapper
import domain.SessionManager
import domain.model.LessonProgress
import domain.model.LessonWithProgress
import ivy.data.source.CoursesDataSource
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
    private val sessionManager: SessionManager,
    private val coursesDataSource: CoursesDataSource,
) : LessonRepository {

    override suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonWithProgress> = either {
        withContext(dispatchers.io) {
            val session = sessionManager.getSession().bind()
            datasource.fetchLesson(
                session = session,
                course = course,
                lesson = lesson
            ).map {
                with(mapper) {
                    it.toDomain()
                }
            }.bind()
        }
    }

    override suspend fun saveLessonProgress(
        course: CourseId,
        lesson: LessonId,
        progress: LessonProgress
    ): Either<String, Unit> = either {
        withContext(dispatchers.io) {
            val session = sessionManager.getSession().bind()
            datasource.saveProgress(
                session = session,
                course = course,
                lesson = lesson,
                progress = LessonProgressDto(
                    selectedAnswers = progress.selectedAnswers,
                    openAnswersInput = progress.openAnswersInput,
                    chosen = progress.chosen,
                    answered = progress.answered,
                    completed = progress.completed,
                )
            ).bind()
        }
    }

    override suspend fun markLessonAsCompleted(
        course: CourseId,
        lesson: LessonId,
    ): Either<String, Unit> = withContext(dispatchers.io) {
        either {
            val session = sessionManager.getSession().bind()
            coursesDataSource.saveProgress(
                session = session,
                course = course,
                lesson = lesson
            ).bind()
        }
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

    suspend fun markLessonAsCompleted(
        course: CourseId,
        lesson: LessonId,
    ): Either<String, Unit>
}