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
import ivy.data.source.model.KpisResponse
import ivy.model.auth.SessionToken

class KpisDataSource(
  private val httpClient: HttpClient,
  private val urlProvider: ServerUrlProvider,
) {
  suspend fun fetchKpis(
    session: SessionToken,
  ): Either<String, KpisResponse> = catch({
    either {
      val response = httpClient.get(
        "${urlProvider.serverUrl}/kpis"
      ) {
        headerSessionToken(session)
        contentType(ContentType.Application.Json)
      }
      ensure(response.status.isSuccess()) {
        "Failed to load KPIs, status - ${response.status}"
      }
      response.body<KpisResponse>()
    }
  }) { e ->
    Either.Left("Failed to load KPIs: ${e.message}")
  }
}