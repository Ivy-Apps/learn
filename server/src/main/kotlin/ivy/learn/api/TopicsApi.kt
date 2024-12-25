package ivy.learn.api

import io.ktor.server.routing.*
import ivy.data.sessionToken
import ivy.data.source.model.TopicsResponse
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import ivy.learn.domain.TopicsService

class TopicsApi(
    private val topicsService: TopicsService,
) : Api {
    override fun Routing.endpoints() {
        loadTopics()
    }

    private fun Routing.loadTopics() {
        getEndpoint<TopicsResponse>("/topics") { headers, _ ->
            topicsService.loadTopics(
                sessionToken = headers.sessionToken()
            ).bind()
        }
    }
}