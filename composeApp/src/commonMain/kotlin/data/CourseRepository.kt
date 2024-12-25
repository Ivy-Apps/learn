package data

import arrow.core.Either
import arrow.core.raise.either
import domain.SessionManager
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.learn.CourseId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class CourseRepositoryImpl(
  private val dispatchers: DispatchersProvider,
  private val dataSource: CoursesDataSource,
  private val sessionManager: SessionManager,
) : CourseRepository {
  override suspend fun fetchCourse(
    courseId: CourseId
  ): Either<String, CourseResponse> = withContext(dispatchers.io) {
    either {
      val session = sessionManager.getSessionToken().getOrNull()
      dataSource.fetchCourseById(
        session = session,
        courseId = courseId,
      ).bind()
    }
  }
}

interface CourseRepository {
  suspend fun fetchCourse(courseId: CourseId): Either<String, CourseResponse>
}