package ui.screen.lesson.handler

import ui.EventHandler
import ui.screen.lesson.LessonViewModel
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.QuestionViewEvent

class QuestionViewEventHandler :
    EventHandler<QuestionViewEvent, LessonViewModel.LocalState> {
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

    }

    private fun LessonVmContext.handleCheckClick(event: QuestionViewEvent.CheckClick) {

    }
}