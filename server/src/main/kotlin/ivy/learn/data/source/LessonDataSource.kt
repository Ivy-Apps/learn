package ivy.learn.data.source

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ivy.model.Lesson
import ivy.model.LessonId

class LessonDataSource(
    private val httpClient: HttpClient
) {
    suspend fun fetchLessonById(id: LessonId): Lesson = httpClient.get(
        urlString = "https://api.github.com/repos/Ivy-Apps/learn-content" +
                "/contents/content/lessons" +
                "/${id.value}.json"
    ) {
        headers {
            append("Authorization", "token ${System.getenv("IVY_LEARN_GITHUB_PAT")}")
            append("Accept", "application/vnd.github.v3+json")
        }
    }.body<Lesson>()
}