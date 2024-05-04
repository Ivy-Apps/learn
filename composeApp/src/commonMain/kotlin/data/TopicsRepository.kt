package data

import arrow.core.Either
import ivy.data.source.TopicsDataSource
import ivy.data.source.model.TopicsResponse
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class TopicsRepository(
    private val dispatchers: DispatchersProvider,
    private val topicsDataSource: TopicsDataSource
) {
    suspend fun fetchTopics(): Either<String, TopicsResponse> = withContext(dispatchers.io) {
        topicsDataSource.fetchTopics()
    }
}