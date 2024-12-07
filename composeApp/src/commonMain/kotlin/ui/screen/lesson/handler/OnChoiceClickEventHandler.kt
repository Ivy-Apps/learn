package ui.screen.lesson.handler

import arrow.optics.copy
import domain.SoundUseCase
import ivy.content.SoundsUrls
import ivy.model.ChoiceOptionId
import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.chosen
import ui.screen.lesson.completed
import ui.screen.lesson.mapper.toDomain

class OnChoiceClickEventHandler(
    private val soundUseCase: SoundUseCase
) : EventHandler<LessonViewEvent.OnChoiceClick, LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnChoiceClick::class)

    override suspend fun LessonVmContext.handleEvent(event: LessonViewEvent.OnChoiceClick) {
        modifyState { state ->
            val questionId = event.questionId.toDomain()
            state.copy {
                LocalState.chosen transform { it + (questionId to ChoiceOptionId(event.choiceId)) }
                LocalState.completed transform { it + questionId }
            }
        }
        soundUseCase.playSound(sound = SoundsUrls.Complete)
    }
}