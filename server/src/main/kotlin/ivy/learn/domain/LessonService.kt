package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.either
import io.ktor.util.logging.*
import ivy.learn.CourseId
import ivy.learn.Lesson.LessonProgressDto
import ivy.learn.Lesson.LessonResponse
import ivy.learn.LessonId
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.LessonProgressRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.domain.auth.AuthService
import ivy.learn.domain.model.LessonProgress
import ivy.model.auth.SessionToken

class LessonService(
    private val authService: AuthService,
    private val lessonsRepository: LessonsRepository,
    private val progressRepository: LessonProgressRepository,
    private val logger: Logger,
) {

    suspend fun loadLesson(
        sessionToken: SessionToken,
        courseId: CourseId,
        lessonId: LessonId,
    ): Either<ServerError, LessonResponse> = either {
        val user = authService.getUser(sessionToken).bind()
        val lesson = lessonsRepository.fetchLesson(courseId, lessonId)
            .onLeft { errMsg ->
                logger.error("Failed to fetch lesson: $errMsg")
            }
            .mapLeft(ServerError::Unknown)
            .bind()
        val progress = progressRepository.findBy(
            userId = user.id,
            lessonId = lessonId,
            courseId = courseId
        ).mapLeft(ServerError::Unknown)
            .onLeft { errMsg ->
                logger.error("Failed to load lesson progress: $errMsg")
            }
            .bind()
        LessonResponse(
            lesson = lesson,
            progress = progress?.state,
        )
    }

    suspend fun saveLessonProgress(
        sessionToken: SessionToken,
        courseId: CourseId,
        lessonId: LessonId,
        dto: LessonProgressDto,
    ): Either<ServerError, Unit> = either {
        val user = authService.getUser(sessionToken).bind()
        progressRepository.upsert(
            progress = LessonProgress(
                userId = user.id,
                courseId = courseId,
                lessonId = lessonId,
                state = dto,
            )
        ).mapLeft(ServerError::Unknown).bind()
    }
}