package ui.screen.lesson.handler

import arrow.optics.copy
import domain.SoundUseCase
import ivy.content.SoundsUrls
import ivy.learn.ChoiceOptionId
import ui.screen.lesson.*
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.mapper.toDomain

class OnChoiceClickEventHandler(
    private val soundUseCase: SoundUseCase
) : LessonEventHandler<LessonViewEvent.OnChoiceClick> {
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