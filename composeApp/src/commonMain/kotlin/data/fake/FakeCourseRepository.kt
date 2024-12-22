package data.fake

import arrow.core.Either
import arrow.core.right
import data.CourseRepository
import ivy.content.EmptyContent
import ivy.data.source.model.CourseResponse
import ivy.learn.*

class FakeCourseRepository : CourseRepository {
  override suspend fun fetchCourse(
    courseId: CourseId
  ): Either<String, CourseResponse> {
    return CourseResponse(
      course = Course(
        id = CourseId("1"),
        name = "Algorithm Foundations",
        tagline = "Demystify space & time complexity, Big O... The first step to becoming an algorithm master.",
        image = ImageUrl("https://i.ibb.co/566dj34/algo-fundamentals.webp"),
        lessons = listOf(
          LessonId(("1")),
          LessonId(("2")),
          LessonId(("3")),
          LessonId(("4")),
          LessonId(("5")),
          LessonId(("6")),
        )
      ),
      lessons = listOf(
        Lesson(
          id = LessonId("1"),
          name = "Time Complexity: What and Why?",
          tagline = "An introduction to Big O and its real-world importance.",
          image = ImageUrl("https://i.ibb.co/njy7FxD/time-complexity-1.webp"),
          content = EmptyContent,
          completed = true,
        ),
        Lesson(
          id = LessonId("2"),
          name = "Fake lesson 2",
          tagline = "Fake lesson for testing purposes",
          image = ImageUrl(Fakes.androidStudioImageUrl),
          content = EmptyContent,
          completed = true,
        ),
        Lesson(
          id = LessonId("3"),
          name = "Fake lesson 3",
          tagline = "Fake lesson for testing purposes",
          image = ImageUrl(Fakes.androidStudioImageUrl),
          content = EmptyContent,
          completed = false,
        ),
        Lesson(
          id = LessonId("4"),
          name = "Fake lesson 4",
          tagline = "Fake lesson for testing purposes",
          image = ImageUrl(Fakes.androidStudioImageUrl),
          content = EmptyContent,
          completed = false,
        ),
        Lesson(
          id = LessonId("5"),
          name = "Fake lesson 5",
          tagline = "Fake lesson for testing purposes",
          image = ImageUrl(Fakes.androidStudioImageUrl),
          content = EmptyContent,
          completed = false,
        ),
        Lesson(
          id = LessonId("6"),
          name = "Fake lesson 6",
          tagline = "Fake lesson for testing purposes",
          image = ImageUrl(Fakes.androidStudioImageUrl),
          content = EmptyContent,
          completed = false,
        ),
      )
    ).right()
  }
}