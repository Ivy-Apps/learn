package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.lesson.LessonProgressDto
import ivy.model.lesson.LessonResponse

class LessonDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonResponse> = catch({
        either {
            val response = httpClient.get(
                "${urlProvider.serverUrl}/lessons/${course.value}/${lesson.value}"
            ) {
                contentType(ContentType.Application.Json)
            }
            ensure(response.status.isSuccess()) {
                "Failed to load lesson, status - ${response.status}"
            }
            response.body<LessonResponse>()
        }
    }) { e ->
        Either.Left("Failed to load lesson: ${e.message}")
    }

    suspend fun saveProgress(
        course: CourseId,
        lesson: LessonId,
        progress: LessonProgressDto,
    ): Either<String, Unit> = catch({
        either {
            val response = httpClient.post(
                "${urlProvider.serverUrl}/lessons/${course.value}/${lesson.value}/progress"
            ) {
                contentType(ContentType.Application.Json)
                setBody(progress)
            }
            ensure(response.status.isSuccess()) {
                "Failed to save lesson progress, status - ${response.status}"
            }
        }
    }) { e ->
        Either.Left("Failed to save lesson progress: $e")
    }
}