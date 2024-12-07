package data.lesson.mapper

import domain.model.LessonProgress
import domain.model.LessonWithProgress
import ivy.model.lesson.LessonResponse

class LessonMapper {
    fun LessonResponse.toDomain(): LessonWithProgress {
        return LessonWithProgress(
            lesson = lesson,
            progress = LessonProgress(
                selectedAnswers = progress?.selectedAnswers ?: emptyMap(),
                openAnswersInput = progress?.openAnswersInput ?: emptyMap(),
                chosen = progress?.chosen ?: emptyMap(),
                answered = progress?.answered ?: emptySet(),
                completed = progress?.completed ?: emptySet()
            )
        )
    }
}