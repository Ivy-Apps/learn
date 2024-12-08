package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.maps.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import ivy.data.source.LessonDataSource
import ivy.di.Di
import ivy.learn.testsupport.ServerTest
import ivy.model.CourseId
import ivy.model.LessonId
import org.junit.Test

class LessonsServerTest : ServerTest() {
    @Test
    fun `fetches existing lesson`() = beTest {
        // Given
        val auth = registerUser("fake@test.com")
        val datasource: LessonDataSource = Di.get()

        // When
        val result = datasource.fetchLesson(
            session = auth.session.token,
            course = CourseId("demo-course"),
            lesson = LessonId("demo-lesson"),
        )

        // Then
        result.shouldBeRight().lesson.id shouldBe LessonId("demo-lesson")
        result.shouldBeRight().lesson.content.items.shouldNotBeEmpty()
    }
}