package data.fake

import arrow.core.Either
import arrow.core.right
import data.lesson.LessonRepository
import domain.model.LessonProgress
import domain.model.LessonWithProgress
import ivy.content.lesson.programmingfundamentals.programmingMathInDisguise
import ivy.model.CourseId
import ivy.model.ImageUrl
import ivy.model.Lesson
import ivy.model.LessonId

class FakeLessonRepository : LessonRepository {
    override suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, LessonWithProgress> {
        return LessonWithProgress(
            lesson = Lesson(
                id = LessonId("fake"),
                name = "Programming: Math in disguise",
                tagline = "",
                image = ImageUrl(""),
                content = programmingMathInDisguise(),
                completed = false,
            ),
            progress = LessonProgress(
                selectedAnswers = mapOf(),
                openAnswersInput = mapOf(),
                chosen = mapOf(),
                answered = setOf(),
                completed = setOf()
            )
        ).right()
    }

    override suspend fun saveLessonProgress(
        course: CourseId,
        lesson: LessonId,
        progress: LessonProgress
    ): Either<String, Unit> {
        return Either.Right(Unit)
    }

    override suspend fun markLessonAsCompleted(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Unit> {
        return Either.Right(Unit)
    }
}