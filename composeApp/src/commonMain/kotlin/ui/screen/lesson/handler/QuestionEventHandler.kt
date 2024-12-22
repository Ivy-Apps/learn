package ui.screen.lesson.handler

import arrow.optics.dsl.index
import arrow.optics.typeclasses.Index
import domain.SoundUseCase
import ivy.content.SoundsUrls
import ivy.learn.AnswerId
import ui.screen.lesson.*
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.QuestionTypeViewState.MultipleChoice
import ui.screen.lesson.QuestionTypeViewState.SingleChoice
import ui.screen.lesson.mapper.toDomain

class QuestionEventHandler(
    private val soundUseCase: SoundUseCase
) : LessonEventHandler<QuestionViewEvent> {
    override val eventTypes = setOf(
        QuestionViewEvent.OnAnswerCheckChange::class,
        QuestionViewEvent.OnCheckClick::class,
    )

    override suspend fun LessonVmContext.handleEvent(
        event: QuestionViewEvent
    ) {
        when (event) {
            is QuestionViewEvent.OnAnswerCheckChange -> handleAnswerCheckChange(event)
            is QuestionViewEvent.OnCheckClick -> handleCheckClick(event)
        }
    }

    private fun LessonVmContext.handleAnswerCheckChange(event: QuestionViewEvent.OnAnswerCheckChange) {
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

    private suspend fun LessonVmContext.handleCheckClick(event: QuestionViewEvent.OnCheckClick) {
        modifyState { state ->
            LocalState.answered.modify(state) { completed ->
                completed + event.questionId.toDomain()
            }
        }
        val correctAnswer = event.answers.all {
            it.selected == it.correct
        }
        soundUseCase.playSound(
            sound = if (correctAnswer) {
                SoundsUrls.Success
            } else {
                SoundsUrls.Ups
            }
        )
    }
}