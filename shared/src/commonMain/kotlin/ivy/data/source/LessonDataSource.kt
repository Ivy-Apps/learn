package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Lesson> = catch({
        httpClient.get(
            "${urlProvider.serverUrl}/lessons/${course.value}/${lesson.value}"
        ) {
            contentType(ContentType.Application.Json)
        }.body<Lesson>().right()
    }) { e ->
        Either.Left("Failed to fetch lesson: ${e.message}")
    }
}