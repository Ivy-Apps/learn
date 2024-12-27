package ui.screen.lesson.handler

import arrow.optics.dsl.index
import arrow.optics.typeclasses.Index
import domain.SoundUseCase
import domain.analytics.Analytics
import ivy.learn.AnswerId
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.QuestionTypeViewState.MultipleChoice
import ui.screen.lesson.QuestionTypeViewState.SingleChoice
import ui.screen.lesson.QuestionViewEvent
import ui.screen.lesson.mapper.toDomain
import ui.screen.lesson.selectedAnswers

class OnAnswerCheckChangeEventHandler(
  private val soundUseCase: SoundUseCase,
  private val analytics: Analytics,
) : LessonEventHandler<QuestionViewEvent.OnAnswerCheckChange> {
  override suspend fun LessonVmContext.handleEvent(
    event: QuestionViewEvent.OnAnswerCheckChange
  ) {
    modifyState { state ->
      val questionId = event.questionId.toDomain()
      val answerId = AnswerId(event.answerId)
      if (questionId in state.selectedAnswers) {
        // Already answered, modify the existing answer
        LocalState.selectedAnswers.index(Index.map(), questionId).modify(state) {
          when (event.questionType) {
            SingleChoice -> if (event.checked) {
              setOf(answerId)
            } else {
              emptySet()
            }

            MultipleChoice -> if (event.checked) {
              it + answerId
            } else {
              it - answerId
            }
          }

        }
      } else {
        // Never answered
        LocalState.selectedAnswers.modify(state) { answers ->
          if (event.checked) {
            answers + Pair(questionId, setOf(answerId))
          } else {
            answers
          }
        }
      }
    }
  }
}