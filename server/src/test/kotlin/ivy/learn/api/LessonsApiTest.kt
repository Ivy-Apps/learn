package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.LessonDataSource
import ivy.di.Di
import ivy.learn.testsupport.ApiTest
import ivy.model.LessonId
import org.junit.Test

class LessonsApiTest : ApiTest() {
    @Test
    fun `fetches existing lesson`() = apiTest {
        // Given
        val datasource = LessonDataSource(Di.get(), Di.get())

        // When
        val result = datasource.fetchLesson(LessonId("test7"))

        // Then
        result.shouldBeRight().id shouldBe "test"
    }
}