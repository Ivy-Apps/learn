package data.fake

import arrow.core.Either
import arrow.core.right
import data.LessonRepository
import ivy.content.lesson.programmingfundamentals.programmingMathInDisguise
import ivy.model.CourseId
import ivy.model.ImageUrl
import ivy.model.Lesson
import ivy.model.LessonId

class FakeLessonRepository : LessonRepository {
    override suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Lesson> {
        return Lesson(
            id = LessonId("fake"),
            name = "Programming: Math in disguise",
            tagline = "",
            image = ImageUrl(""),
            content = programmingMathInDisguise(),
        ).right()
    }
}