package ivy.learn.domain

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import ivy.data.source.model.TopicsResponse
import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.learn.api.common.model.ServerError
import ivy.learn.data.repository.CourseProgressRepository
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.TopicsRepository
import ivy.learn.domain.auth.AuthService
import ivy.learn.domain.model.User
import ivy.model.Course
import ivy.model.auth.SessionToken

class TopicsService(
    private val topicsRepository: TopicsRepository,
    private val coursesRepository: CoursesRepository,
    private val courseProgressRepository: CourseProgressRepository,
    private val authService: AuthService,
) {
    suspend fun loadTopics(
        sessionToken: SessionToken,
    ): Either<ServerError, TopicsResponse> = either {
        val user = authService.getUser(sessionToken).bind()
        val courses = coursesRepository.fetchCourses()

        TopicsResponse(
            topics = topicsRepository.fetchTopics(),
            courses = courses,
            progress = loadCoursesProgress(
                user = user,
                courses = courses,
            )
        )
    }

    private fun Raise<ServerError>.loadCoursesProgress(
        user: User,
        courses: List<Course>,
    ): Map<CourseId, Set<LessonId>> {
        val progress = courseProgressRepository.findBy(
            userId = user.id,
            courses = courses.map(Course::id)
        ).mapLeft(ServerError::Unknown)
            .bind()

        return buildMap {
            for (progressItem in progress) {
                val courseId = progressItem.courseId
                put(courseId, get(courseId).orEmpty() + progressItem.lessonId)
            }
        }
    }
}