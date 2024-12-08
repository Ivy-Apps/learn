package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.data.headerSessionToken
import ivy.data.source.model.CourseResponse
import ivy.model.CourseId
import ivy.model.auth.SessionToken

class CoursesDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun fetchCourseById(
        session: SessionToken,
        courseId: CourseId
    ): Either<String, CourseResponse> = catch({
        httpClient.get(
            "${urlProvider.serverUrl}/courses/${courseId.value}"
        ) {
            contentType(ContentType.Application.Json)
            headerSessionToken(session)
        }.body<CourseResponse>().right()
    }) { e ->
        Either.Left("Failed to fetch '${courseId.value}' course: ${e.message}")
    }
}