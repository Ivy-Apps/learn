package data

import arrow.core.Either
import arrow.core.raise.either
import domain.SessionManager
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class CourseRepository(
    private val dispatchers: DispatchersProvider,
    private val dataSource: CoursesDataSource,
    private val sessionManager: SessionManager,
) {
    suspend fun fetchCourse(
        courseId: CourseId
    ): Either<String, CourseResponse> = withContext(dispatchers.io) {
        either {
            val session = sessionManager.getSession().bind()
            dataSource.fetchCourseById(
                session = session,
                courseId = courseId,
            ).bind()
        }
    }
}