package ivy.learn.api

import io.ktor.server.routing.*
import ivy.data.source.model.TopicsResponse
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpoint
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.TopicsRepository

class TopicsApi(
    private val topicsRepository: TopicsRepository,
    private val coursesRepository: CoursesRepository
) : Api {
    override fun Routing.endpoints() {
        topics()
    }

    private fun Routing.topics() {
        getEndpoint<TopicsResponse>("/topics") {
            TopicsResponse(
                topics = topicsRepository.fetchTopics(),
                courses = coursesRepository.fetchCourses()
            )
        }
    }
}