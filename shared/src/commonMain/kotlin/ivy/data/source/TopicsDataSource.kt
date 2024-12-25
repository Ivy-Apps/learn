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
import ivy.data.headerSessionToken
import ivy.data.source.model.TopicsResponse
import ivy.model.auth.SessionToken

class TopicsDataSource(
  private val httpClient: HttpClient,
  private val urlProvider: ServerUrlProvider,
) {
  suspend fun fetchTopics(
    session: SessionToken?,
  ): Either<String, TopicsResponse> = catch({
    either {
      val response = httpClient.get(
        "${urlProvider.serverUrl}/topics"
      ) {
        contentType(ContentType.Application.Json)
        if (session != null) {
          headerSessionToken(session)
        }
      }
      ensure(response.status.isSuccess()) {
        "Failed to fetch topics, status - ${response.status}"
      }
      response.body<TopicsResponse>()
    }
  }) { e ->
    Either.Left("Failed to fetch topics: ${e.message}")
  }
}