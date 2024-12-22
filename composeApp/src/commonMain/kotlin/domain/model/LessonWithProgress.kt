package domain.model

import ivy.learn.AnswerId
import ivy.learn.ChoiceOptionId
import ivy.learn.Lesson
import ivy.learn.LessonItemId

data class LessonWithProgress(
  val lesson: Lesson,
  val progress: LessonProgress,
)

data class LessonProgress(
  val selectedAnswers: Map<LessonItemId, Set<AnswerId>>,
  val openAnswersInput: Map<LessonItemId, String>,
  val chosen: Map<LessonItemId, ChoiceOptionId>,
  val answered: Set<LessonItemId>,
  val completed: Set<LessonItemId>,
)