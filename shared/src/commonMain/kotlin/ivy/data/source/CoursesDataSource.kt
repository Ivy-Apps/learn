package ivy.data.source

import IvyConstants
import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.data.headerSessionToken
import ivy.data.source.model.CourseResponse
import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.model.auth.SessionToken
import ivy.model.course.CourseProgressRequest

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

  suspend fun saveProgress(
    session: SessionToken,
    course: CourseId,
    lesson: LessonId,
  ): Either<String, Unit> = catch({
    either {
      val response = httpClient.post(
        "${urlProvider.serverUrl}/lessons/${course.value}/progress"
      ) {
        header(IvyConstants.HEADER_SESSION_TOKEN, session.value)
        contentType(ContentType.Application.Json)
        setBody(
          CourseProgressRequest(
            completedLesson = lesson,
          )
        )
      }
      ensure(response.status.isSuccess()) {
        "Failed to save course progress, status - ${response.status}"
      }
    }
  }) { e ->
    Either.Left("Failed to save course progress: $e")
  }
}