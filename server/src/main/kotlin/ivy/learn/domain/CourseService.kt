package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import ivy.data.source.model.CourseResponse
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.CourseProgressRepository
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.domain.auth.AuthenticationService
import ivy.learn.domain.model.CompletedLesson
import ivy.learn.domain.model.UserId
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.auth.SessionToken

class CourseService(
    private val authService: AuthenticationService,
    private val coursesRepository: CoursesRepository,
    private val lessonsRepository: LessonsRepository,
    private val courseProgressRepository: CourseProgressRepository,
) {
    suspend fun loadCourse(
        sessionToken: SessionToken,
        courseId: CourseId
    ): Either<ServerError, CourseResponse> = either {
        val course = coursesRepository.fetchCourseById(courseId)
        ensureNotNull(course) {
            ServerError.BadRequest("Course not found for id $courseId")
        }
        val user = authService.getUser(sessionToken).bind()
        val lessons = lessonsRepository.fetchPartialLessons(courseId)
            .mapLeft(ServerError::BadRequest)
            .bind()
        val lessonsWithProgress = markCompletedLessons(
            userId = user.id,
            courseId = course.id,
            lessons = lessons,
        )
        CourseResponse(
            course = course,
            lessons = lessonsWithProgress,
        )
    }

    private fun Raise<ServerError>.markCompletedLessons(
        userId: UserId,
        courseId: CourseId,
        lessons: List<Lesson>,
    ): List<Lesson> {
        val completedLessons = courseProgressRepository.findBy(
            userId = userId,
            courses = listOf(courseId)
        ).mapLeft(ServerError::Unknown)
            .bind()
            .map(CompletedLesson::lessonId)
            .toSet()
        return lessons.map { lesson ->
            lesson.copy(
                completed = lesson.id in completedLessons,
            )
        }
    }
}