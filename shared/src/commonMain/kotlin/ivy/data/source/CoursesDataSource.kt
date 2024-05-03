package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId

class CoursesDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun fetchCourseById(id: CourseId): Either<String, CourseResponse> = catch({
        httpClient.get(
            "${urlProvider.serverUrl}/courses/${id.value}"
        ) {
            contentType(ContentType.Application.Json)
        }.body<CourseResponse>().right()
    }) { e ->
        Either.Left("Failed to fetch '${id.value}' course: ${e.message}")
    }
}