package ivy.learn.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ivy.learn.ServerConfiguration
import ivy.model.Lesson
import ivy.model.LessonId

class LessonDataSource(
    private val httpClient: HttpClient,
    private val config: ServerConfiguration,
) {
    suspend fun fetchLessonById(id: LessonId): Either<String, Lesson> = catch({
        httpClient.get(
            urlString = "https://raw.githubusercontent.com/Ivy-Apps/learn-content" +
                    "/main/content/lessons" +
                    "/${id.value}.json"
        ) {
            headers {
                append("Authorization", "token ${config.privateContentGitHubPat}")
                append("Accept", "application/vnd.github.v3+json")
            }
        }.body<Lesson>().right()
    }) { e ->
        Either.Left("Failed to fetch lesson: ${e.message}")
    }
}