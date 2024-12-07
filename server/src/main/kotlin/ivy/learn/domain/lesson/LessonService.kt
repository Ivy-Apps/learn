package ivy.learn.domain.lesson

import arrow.core.Either
import arrow.core.raise.either
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.LessonProgressRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.domain.auth.AuthenticationService
import ivy.learn.domain.model.LessonProgress
import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.auth.SessionToken
import ivy.model.lesson.LessonProgressDto
import ivy.model.lesson.LessonResponse

class LessonService(
    private val authService: AuthenticationService,
    private val lessonsRepository: LessonsRepository,
    private val progressRepository: LessonProgressRepository,
) {

    suspend fun loadLesson(
        sessionToken: SessionToken,
        courseId: CourseId,
        lessonId: LessonId,
    ): Either<ServerError, LessonResponse> = either {
        val user = authService.getUser(sessionToken).bind()
        val lesson = lessonsRepository.fetchLesson(courseId, lessonId)
            .mapLeft(ServerError::Unknown).bind()
        val progress = progressRepository.findBy(
            userId = user.id,
            lessonId = lessonId,
            courseId = courseId
        ).mapLeft(ServerError::Unknown).bind()
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
        progressRepository.insert(
            progress = LessonProgress(
                userId = user.id,
                courseId = courseId,
                lessonId = lessonId,
                state = dto,
            )
        ).mapLeft(ServerError::Unknown).bind()
    }
}