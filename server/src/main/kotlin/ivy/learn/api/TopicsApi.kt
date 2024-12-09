package ivy.learn.api

import io.ktor.server.routing.*
import ivy.data.source.model.TopicsResponse
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointAuthenticated
import ivy.learn.domain.TopicsService

class TopicsApi(
    private val topicsService: TopicsService,
) : Api {
    override fun Routing.endpoints() {
        loadTopics()
    }

    private fun Routing.loadTopics() {
        getEndpointAuthenticated<TopicsResponse>("/topics") { _, sessionToken ->
            topicsService.loadTopics(
                sessionToken = sessionToken
            ).bind()
        }
    }
}