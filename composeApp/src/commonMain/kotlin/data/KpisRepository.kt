package data

import arrow.core.Either
import arrow.core.raise.either
import domain.SessionManager
import ivy.data.source.KpisDataSource
import ivy.data.source.model.KpisResponse
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class KpisRepository(
  private val dataSource: KpisDataSource,
  private val sessionManager: SessionManager,
  private val dispatchersProvider: DispatchersProvider,
) {
  suspend fun fetchKpis(): Either<String, KpisResponse> = withContext(dispatchersProvider.io) {
    either {
      val session = sessionManager.getSessionToken().bind()
      dataSource.fetchKpis(session = session).bind()
    }
  }
}