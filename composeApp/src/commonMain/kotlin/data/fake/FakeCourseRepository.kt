package data.fake

import arrow.core.Either
import arrow.core.right
import data.CourseRepository
import ivy.data.source.model.CourseResponse
import ivy.model.Course
import ivy.model.CourseId
import ivy.model.ImageUrl
import ivy.model.LessonId
import kotlinx.coroutines.delay

class FakeCourseRepository : CourseRepository {
    override suspend fun fetchCourse(
        courseId: CourseId
    ): Either<String, CourseResponse> {
        delay(1_000)
        return CourseResponse(
            course = Course(
                id = CourseId("1"),
                name = "Fake course",
                tagline = "This is a fake course used for testing purposes",
                image = ImageUrl("fake"),
                lessons = listOf(
                    LessonId(("1")),
                )
            ),
            lessons = listOf()
        ).right()
    }
}