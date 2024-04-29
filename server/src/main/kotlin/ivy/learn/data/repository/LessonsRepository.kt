package ivy.learn.data.repository

import arrow.core.Either
import io.ktor.client.*
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsRepository(
    private val httpClient: HttpClient
) {
    fun fetchLessonById(id: LessonId): Either<String, Lesson> = TODO()
}