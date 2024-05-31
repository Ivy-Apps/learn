package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.di.Di
import ivy.learn.data.cms.course.programming.ProgrammingFundamentals
import ivy.learn.testsupport.ApiTest
import org.junit.Test

class CoursesApiTest : ApiTest() {
    @Test
    fun `fetches topics`() = apiTest {
        // Given
        val datasource: CoursesDataSource = Di.get()

        // When
        val result = datasource.fetchCourseById(ProgrammingFundamentals.course.id)

        // Then
        result.shouldBeRight() shouldBe CourseResponse(
            course = ProgrammingFundamentals.course,
            lessons = ProgrammingFundamentals.lessons,
        )
    }
}