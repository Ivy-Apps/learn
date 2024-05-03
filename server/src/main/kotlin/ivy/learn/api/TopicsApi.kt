package ivy.learn.api

import io.ktor.server.routing.*
import io.ktor.util.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.endpoint
import ivy.model.CourseId
import ivy.model.Topic
import ivy.model.TopicId

class TopicsApi : Api {
    override fun Routing.endpoints() {
        topics()
    }

    @KtorDsl
    private fun Routing.topics() {
        get("/topics", endpoint {
            listOf(
                Topic(
                    id = TopicId("programming_fundamentals"),
                    name = "Programming Fundamentals",
                    courses = listOf(
                        CourseId("programming_fundamentals_101"),
                    )
                ),
            )
        })
    }
}