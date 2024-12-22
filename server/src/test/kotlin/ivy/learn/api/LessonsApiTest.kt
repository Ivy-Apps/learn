package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.maps.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import ivy.data.source.LessonDataSource
import ivy.di.Di
import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.learn.LessonItemId
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.data.repository.LessonProgressFixtures
import org.junit.Test

class LessonsApiTest : ServerTest() {
    @Test
    fun `fetch existing lesson`() = beTest {
        // Given
        val auth = registerUser()
        val datasource = Di.get<LessonDataSource>()
        val courseId = CourseId("demo-course")
        val lessonId = LessonId("demo-lesson")


        // When
        val response = datasource.fetchLesson(
            session = auth.session.token,
            course = courseId,
            lesson = lessonId,
        )

        // Then
        val successResponse = response.shouldBeRight()
        successResponse.lesson.id shouldBe lessonId
        successResponse.lesson.content.items.shouldNotBeEmpty()
    }

    @Test
    fun `saves lesson progress`() = beTest {
        // Given
        val auth = registerUser()
        val datasource = Di.get<LessonDataSource>()
        val courseId = CourseId("demo-course")
        val lessonId = LessonId("demo-lesson")

        // When
        val response1 = datasource.saveProgress(
            session = auth.session.token,
            course = courseId,
            lesson = lessonId,
            progress = LessonProgressFixtures.state(
                openAnswersInput = mapOf(
                    LessonItemId("1") to "42"
                )
            )
        )
        val response2 = datasource.saveProgress(
            session = auth.session.token,
            course = courseId,
            lesson = lessonId,
            progress = LessonProgressFixtures.state(
                openAnswersInput = mapOf(
                    LessonItemId("1") to "3.14"
                )
            )
        )

        // Then
        response1.shouldBeRight()
        response2.shouldBeRight()
    }
}