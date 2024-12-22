package ivy.learn.data.repository

import ivy.learn.Topic
import ivy.learn.data.cms.TopicsContent

class TopicsRepository {
    fun fetchTopics(): List<Topic> = TopicsContent.topics
}