package data

import arrow.core.Either
import arrow.core.raise.either
import domain.SessionManager
import ivy.data.source.TopicsDataSource
import ivy.data.source.model.TopicsResponse
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class TopicsRepository(
    private val dispatchers: DispatchersProvider,
    private val topicsDataSource: TopicsDataSource,
    private val sessionManager: SessionManager,
) {
    suspend fun fetchTopics(): Either<String, TopicsResponse> = withContext(dispatchers.io) {
        either {
            val session = sessionManager.getSession()
            topicsDataSource.fetchTopics(
                session = session.bind()
            ).bind()
        }
    }
}