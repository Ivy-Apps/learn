package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.source.CoursesDataSource
import ivy.data.source.TopicsDataSource
import ivy.data.source.model.TopicsResponse
import ivy.di.Di
import ivy.learn.data.cms.TopicsContent
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.testsupport.ServerTest
import org.junit.Test

class TopicsApiTest : ServerTest() {
  @Test
  fun `fetches topics`() = beTest {
    // Given
    val auth = registerUser()
    val datasource = Di.get<TopicsDataSource>()
    val courseDatasource = Di.get<CoursesDataSource>()
    val firstCourse = CoursesContent.courses.first()

    // When
    courseDatasource.saveProgress(
      session = auth.session.token,
      course = firstCourse.id,
      lesson = firstCourse.lessons.first()
    )
    val result = datasource.fetchTopics(
      session = auth.session.token,
    )

    // Then
    result.shouldBeRight() shouldBe TopicsResponse(
      topics = TopicsContent.topics,
      courses = CoursesContent.courses,
      progress = mapOf(
        firstCourse.id to setOf(firstCourse.lessons.first())
      ),
    )
  }

  @Test
  fun `fetches topics logged-out`() = beTest {
    // Given
    val datasource = Di.get<TopicsDataSource>()

    // When
    val result = datasource.fetchTopics(session = null)

    // Then
    result.shouldBeRight() shouldBe TopicsResponse(
      topics = TopicsContent.topics,
      courses = CoursesContent.courses,
      progress = emptyMap(),
    )
  }
}