package data

import arrow.core.Either
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class CourseRepository(
    private val dispatchers: DispatchersProvider,
    private val dataSource: CoursesDataSource
) {
    suspend fun fetchCourse(
        id: CourseId
    ): Either<String, CourseResponse> = withContext(dispatchers.io) {
        dataSource.fetchCourseById(id)
    }
}