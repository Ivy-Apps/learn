package domain.model

import arrow.optics.optics
import ivy.model.AnswerId
import ivy.model.ChoiceOptionId
import ivy.model.Lesson
import ivy.model.LessonItemId

data class LessonWithProgress(
    val lesson: Lesson,
    val progress: LessonProgress,
)

@optics
data class LessonProgress(
    val selectedAnswers: Map<LessonItemId, Set<AnswerId>>,
    val openAnswersInput: Map<LessonItemId, String>,
    val chosen: Map<LessonItemId, ChoiceOptionId>,
    val answered: Set<LessonItemId>,
    val completed: Set<LessonItemId>,
)