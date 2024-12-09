package ui.screen.lesson.handler

import domain.SoundUseCase
import ivy.content.SoundsUrls
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.completed
import ui.screen.lesson.mapper.toDomain

class OnContinueClickEventHandler(
    private val soundUseCase: SoundUseCase
) : LessonEventHandler<LessonViewEvent.OnContinueClick> {
    override val eventTypes = setOf(LessonViewEvent.OnContinueClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnContinueClick
    ) {
        modifyState { state ->
            LocalState.completed.modify(state) { completed ->
                completed + event.currentItemId.toDomain()
            }
        }
        soundUseCase.playSound(SoundsUrls.Complete)
    }
}