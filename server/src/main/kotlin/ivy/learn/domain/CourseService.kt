package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import ivy.data.source.model.CourseResponse
import ivy.learn.CourseId
import ivy.learn.Lesson
import ivy.learn.LessonId
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.CourseProgressRepository
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.domain.auth.AuthService
import ivy.learn.domain.model.CompletedLesson
import ivy.learn.domain.model.UserId
import ivy.learn.util.TimeProvider
import ivy.letCo
import ivy.model.auth.SessionToken

class CourseService(
  private val authService: AuthService,
  private val coursesRepository: CoursesRepository,
  private val lessonsRepository: LessonsRepository,
  private val courseProgressRepository: CourseProgressRepository,
  private val timeProvider: TimeProvider,
) {
  suspend fun loadCourse(
    sessionToken: SessionToken?,
    courseId: CourseId
  ): Either<ServerError, CourseResponse> = either {
    val course = coursesRepository.fetchCourseById(courseId)
    ensureNotNull(course) {
      ServerError.BadRequest("Course not found for id $courseId")
    }
    val user = sessionToken?.letCo(authService::getUser)?.bind()
    val lessons = lessonsRepository.fetchPartialLessons(courseId)
      .mapLeft(ServerError::BadRequest)
      .bind()
    CourseResponse(
      course = course,
      lessons = if (user != null) {
        lessonWithProgress(
          userId = user.id,
          courseId = course.id,
          lessons = lessons,
        )
      } else {
        lessons
      },
    )
  }

  private fun Raise<ServerError>.lessonWithProgress(
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

  suspend fun saveCourseProgress(
    sessionToken: SessionToken,
    courseId: CourseId,
    lessonId: LessonId,
  ): Either<ServerError, Unit> = either {
    val user = authService.getUser(sessionToken).bind()
    courseProgressRepository.insert(
      completedLesson = CompletedLesson(
        userId = user.id,
        courseId = courseId,
        lessonId = lessonId,
        time = timeProvider.instantNow(),
      )
    ).mapLeft(ServerError::Unknown)
      .bind()
  }
}