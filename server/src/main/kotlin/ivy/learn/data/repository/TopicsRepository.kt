package ivy.learn.data.repository

import ivy.learn.data.cms.TopicsContent
import ivy.model.Topic

class TopicsRepository {
    fun fetchTopics(): List<Topic> = TopicsContent.topics
}