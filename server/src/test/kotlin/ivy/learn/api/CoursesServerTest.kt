package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.di.Di
import ivy.learn.data.cms.course.programming.ProgrammingFundamentals
import ivy.learn.testsupport.ServerTest
import org.junit.Test

class CoursesServerTest : ServerTest() {
    @Test
    fun `fetches topics`() = beTest {
        // Given
        val auth = registerUser()
        val datasource: CoursesDataSource = Di.get()

        // When
        val result = datasource.fetchCourseById(
            session = auth.session.token,
            courseId = ProgrammingFundamentals.course.id
        )

        // Then
        result.shouldBeRight() shouldBe CourseResponse(
            course = ProgrammingFundamentals.course,
            lessons = ProgrammingFundamentals.lessons,
        )
    }
}