package ivy.data.source

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.data.source.model.TopicsResponse

class TopicsDataSource(
    private val httpClient: HttpClient,
    private val urlProvider: ServerUrlProvider,
) {
    suspend fun fetchTopics(): Either<String, TopicsResponse> = catch({
        httpClient.get(
            "${urlProvider.serverUrl}/topics"
        ) {
            contentType(ContentType.Application.Json)
        }.body<TopicsResponse>().right()
    }) { e ->
        Either.Left("Failed to fetch topics: ${e.message}")
    }
}