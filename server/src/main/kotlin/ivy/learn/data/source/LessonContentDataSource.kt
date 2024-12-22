package ivy.learn.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ivy.learn.CourseId
import ivy.learn.LessonContent
import ivy.learn.LessonId
import ivy.learn.config.ServerConfiguration

class LessonContentDataSource(
    private val httpClient: HttpClient,
    private val config: ServerConfiguration,
) {
    suspend fun fetchLessonContent(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonContent> = catch({
        val url = "https://raw.githubusercontent.com/Ivy-Apps/learn-content" +
                "/main/content/lessons" +
                "/${course.value}/${lesson.value}.json"
        httpClient.get(
            urlString = url
        ) {
            headers {
                append("Authorization", "token ${config.privateContentGitHubPat}")
                append("Accept", "application/vnd.github.v3+json")
            }
        }.let {
            when (it.status.value) {
                404 -> Either.Left("Lesson content for '$url' not found")
                else -> it.body<LessonContent>().right()
            }
        }
    }) { e ->
        Either.Left("Failed to fetch lesson content: ${e.message}")
    }
}