package ivy.learn.api

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import ivy.data.source.CoursesDataSource
import ivy.di.Di
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.testsupport.ServerTest
import org.junit.Test

class CoursesApiTest : ServerTest() {
  @Test
  fun `fetches courses`() = beTest {
    CoursesContent.courses.forEach { course ->
      // Given
      val auth = registerUser()
      val datasource = Di.get<CoursesDataSource>()
      val courseId = course.id

      // When
      val result = datasource.fetchCourseById(
        session = auth.session.token,
        courseId = courseId
      )

      // Then
      withClue("courseId = ${courseId.value}") {
        result.shouldBeRight().lessons.shouldNotBeEmpty()
      }
    }
  }

  @Test
  fun `fetches courses logged-out`() = beTest {
    CoursesContent.courses.forEach { course ->
      // Given
      val datasource = Di.get<CoursesDataSource>()
      val courseId = course.id

      // When
      val result = datasource.fetchCourseById(
        session = null,
        courseId = courseId
      )

      // Then
      withClue("courseId = ${courseId.value}") {
        result.shouldBeRight().lessons.shouldNotBeEmpty()
      }
    }
  }

  @Test
  fun `saves course progress`() = beTest {
    // Given
    val auth = registerUser()
    val datasource = Di.get<CoursesDataSource>()
    val course = CoursesContent.courses.first()

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