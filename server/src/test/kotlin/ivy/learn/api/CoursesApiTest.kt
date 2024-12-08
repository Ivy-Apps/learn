package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.data.source.CoursesDataSource
import ivy.data.source.model.CourseResponse
import ivy.di.Di
import ivy.learn.data.cms.course.programming.ProgrammingFundamentals
import ivy.learn.testsupport.ServerTest
import org.junit.Test

class CoursesApiTest : ServerTest() {
    @Test
    fun `fetches course`() = beTest {
        // Given
        val auth = registerUser()
        val datasource = Di.get<CoursesDataSource>()

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

    @Test
    fun `saves course progress`() = beTest {
        // Given
        val auth = registerUser()
        val datasource = Di.get<CoursesDataSource>()
        val course = ProgrammingFundamentals.course

        // When
        val progressResponse = datasource.saveProgress(
            session = auth.session.token,
            course = course.id,
            lesson = course.lessons.first()
        )
        val courseResponse = datasource.fetchCourseById(
            session = auth.session.token,
            courseId = course.id,
        )

        // Then
        progressResponse.shouldBeRight()
        courseResponse.shouldBeRight().lessons.first().completed shouldBe true
    }
}