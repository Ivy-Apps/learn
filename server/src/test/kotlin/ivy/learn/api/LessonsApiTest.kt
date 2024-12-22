package ivy.learn.api

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.withClue
import io.kotest.matchers.maps.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import ivy.data.source.LessonDataSource
import ivy.di.Di
import ivy.learn.CourseId
import ivy.learn.LessonId
import ivy.learn.LessonItemId
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.data.repository.LessonProgressFixtures
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class LessonsApiTest : ServerTest() {

  enum class ValidLessonTestCase(
    val courseId: CourseId,
    val lessonId: LessonId,
  ) {
    ALGORITHM_FOUNDATIONS__TIME_COMPLEXITY_WHAT_AND_WHY(
      courseId = CourseId("algorithm-foundations"),
      lessonId = LessonId("time-complexity-what-and-why")
    ),
    ALGORITHM_FOUNDATIONS__TIME_COMPLEXITY_TYPES(
      courseId = CourseId("algorithm-foundations"),
      lessonId = LessonId("time-complexity-types")
    ),
  }

  @Test
  fun `fetch existing lesson`(
    @TestParameter testCase: ValidLessonTestCase,
  ) = beTest {
    // Given
    val auth = registerUser()
    val datasource = Di.get<LessonDataSource>()


    // When
    val response = datasource.fetchLesson(
      session = auth.session.token,
      course = testCase.courseId,
      lesson = testCase.lessonId,
    )

    // Then
    val successResponse = response.shouldBeRight()
    successResponse.lesson.id shouldBe testCase.lessonId
    successResponse.lesson.content.items.shouldNotBeEmpty()
  }

  @Test
  fun `validate all active lessons`() = beTest {
    CoursesContent.courses.flatMap { course ->
      course.lessons.map { lessonId ->
        course.id to lessonId
      }
    }.forEach { (courseId, lessonId) ->

      // Given
      val auth = registerUser()
      val datasource = Di.get<LessonDataSource>()


      // When
      val response = datasource.fetchLesson(
        session = auth.session.token,
        course = courseId,
        lesson = lessonId,
      )

      // Then
      withClue("courseId = ${courseId.value}, lessonId = ${lessonId.value}") {
        val successResponse = response.shouldBeRight()
        successResponse.lesson.id shouldBe lessonId
        successResponse.lesson.content.items.shouldNotBeEmpty()
      }
    }
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