package ivy.learn.testsupport.data.repository

import ivy.model.AnswerId
import ivy.model.ChoiceOptionId
import ivy.model.LessonItemId
import ivy.model.lesson.LessonProgressDto

object LessonProgressFixtures {
    fun state(
        selectedAnswers: Map<LessonItemId, Set<AnswerId>> = emptyMap(),
        openAnswersInput: Map<LessonItemId, String> = emptyMap(),
        chosen: Map<LessonItemId, ChoiceOptionId> = emptyMap(),
        answered: Set<LessonItemId> = emptySet(),
        completed: Set<LessonItemId> = emptySet(),
    ) = LessonProgressDto(
        selectedAnswers = selectedAnswers,
        openAnswersInput = openAnswersInput,
        chosen = chosen,
        answered = answered,
        completed = completed,
    )
}