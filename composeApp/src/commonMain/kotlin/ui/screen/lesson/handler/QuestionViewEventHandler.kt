package ui.screen.lesson.handler

import arrow.optics.dsl.index
import arrow.optics.typeclasses.Index
import ivy.model.AnswerId
import ui.EventHandler
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.QuestionTypeViewState.MultipleChoice
import ui.screen.lesson.QuestionTypeViewState.SingleChoice
import ui.screen.lesson.QuestionViewEvent
import ui.screen.lesson.answers
import ui.screen.lesson.completed
import ui.screen.lesson.mapper.toDomain

class QuestionViewEventHandler :
    EventHandler<QuestionViewEvent, LocalState> {
    override val eventTypes = setOf(
        QuestionViewEvent.AnswerCheckChange::class,
        QuestionViewEvent.CheckClick::class,
    )

    override suspend fun LessonVmContext.handleEvent(
        event: QuestionViewEvent
    ) {
        when (event) {
            is QuestionViewEvent.AnswerCheckChange -> handleAnswerCheckChange(event)
            is QuestionViewEvent.CheckClick -> handleCheckClick(event)
        }
    }

    private fun LessonVmContext.handleAnswerCheckChange(event: QuestionViewEvent.AnswerCheckChange) {
        modifyState { state ->
            val questionId = event.questionId.toDomain()
            val answerId = AnswerId(event.answerId)
            if (questionId in state.answers) {
                // Already answered, modify the existing answer
                LocalState.answers.index(Index.map(), questionId).modify(state) {
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
                LocalState.answers.modify(state) { answers ->
                    if (event.checked) {
                        answers + Pair(questionId, setOf(answerId))
                    } else {
                        answers
                    }
                }
            }
        }
    }

    private fun LessonVmContext.handleCheckClick(event: QuestionViewEvent.CheckClick) {
        modifyState { state ->
            LocalState.completed.modify(state) { completed ->
                completed + event.questionId.toDomain()
            }
        }
    }
}