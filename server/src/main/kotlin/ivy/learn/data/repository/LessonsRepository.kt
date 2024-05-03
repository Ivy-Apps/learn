package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.either
import ivy.learn.data.source.LessonContentDataSource
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsRepository(
    private val lessonContentDataSource: LessonContentDataSource
) {
    suspend fun fetchLesson(
        course: CourseId,
        lesson: LessonId
    ): Either<String, Lesson> = either {
        val content = lessonContentDataSource.fetchLessonById(course, lesson).bind()
        Lesson(
            id = lesson,
            name = "",
            tagline = "",
            content = content
        )
    }

}