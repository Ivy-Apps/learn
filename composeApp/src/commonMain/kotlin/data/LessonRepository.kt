package data

import arrow.core.Either
import arrow.core.right
import ivy.data.source.LessonDataSource
import ivy.learn.data.cms.lesson.programmingfundamentals.programmingMathInDisguise
import ivy.model.CourseId
import ivy.model.ImageUrl
import ivy.model.Lesson
import ivy.model.LessonId
import kotlinx.coroutines.withContext
import util.DispatchersProvider

class LessonRepository(
    private val dispatchers: DispatchersProvider,
    private val datasource: LessonDataSource,
) {
    private val fakeLessonEnabled = true

    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Lesson> = withContext(dispatchers.io) {
        fakeLesson()?.right() ?: datasource.fetchLesson(course, lesson)
    }

    private fun fakeLesson(): Lesson? = if (fakeLessonEnabled) {
        Lesson(
            id = LessonId("fake"),
            name = "Programming: Math in disguise",
            tagline = "",
            image = ImageUrl(""),
            content = programmingMathInDisguise(),
        )
    } else null
}