package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.source.TopicsDataSource
import ivy.data.source.model.TopicsResponse
import ivy.di.Di
import ivy.learn.data.cms.TopicsContent
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.testsupport.ApiTest
import org.junit.Test

class TopicsApiTest : ApiTest() {
    @Test
    fun `fetches topics`() = apiTest {
        // Given
        val datasource: TopicsDataSource = Di.get()

        // When
        val result = datasource.fetchTopics()

        // Then
        result.shouldBeRight() shouldBe TopicsResponse(
            topics = TopicsContent.topics,
            courses = CoursesContent.courses
        )
    }
}