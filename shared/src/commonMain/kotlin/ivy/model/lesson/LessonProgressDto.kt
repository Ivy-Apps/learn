package ivy.model.lesson

import ivy.learn.AnswerId
import ivy.learn.ChoiceOptionId
import ivy.learn.LessonItemId
import kotlinx.serialization.Serializable

@Serializable
data class LessonProgressDto(
    val selectedAnswers: Map<LessonItemId, Set<AnswerId>>,
    val openAnswersInput: Map<LessonItemId, String>,
    val chosen: Map<LessonItemId, ChoiceOptionId>,
    val answered: Set<LessonItemId>,
    val completed: Set<LessonItemId>,
)