package data.fake

import arrow.core.Either
import arrow.core.right
import data.TopicsRepository
import ivy.data.source.model.TopicsResponse
import ivy.learn.*

class FakeTopicsRepository : TopicsRepository {
  override suspend fun fetchTopics(): Either<String, TopicsResponse> {
    val courseId = CourseId("fake")
    return TopicsResponse(
      topics = listOf(
        Topic(
          id = TopicId("1"),
          name = "Fake topic",
          courses = listOf(
            courseId,
          ),
        )
      ),
      courses = listOf(
        Course(
          id = courseId,
          name = "Algorithm Foundations",
          tagline = "Demystify space & time complexity, Big O... The first step to becoming an algorithm master.",
          image = ImageUrl("https://i.ibb.co/566dj34/algo-fundamentals.webp"),
          lessons = listOf(
            LessonId("1"),
            LessonId("2"),
            LessonId("3"),
            LessonId("4"),
            LessonId("5"),
          )
        )
      ),
      progress = mapOf(
        courseId to setOf(
          LessonId("1"), LessonId("2"), LessonId("3")
        )
      )
    ).right()
  }
}