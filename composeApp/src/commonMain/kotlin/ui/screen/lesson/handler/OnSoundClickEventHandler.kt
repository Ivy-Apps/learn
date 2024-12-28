package ui.screen.lesson.handler

import domain.SoundUseCase
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonVmContext

class OnSoundClickEventHandler(
    private val soundUseCase: SoundUseCase
) : LessonEventHandler<LessonViewEvent.OnSoundClick> {
    override suspend fun LessonVmContext.handleEvent(event: LessonViewEvent.OnSoundClick) {
        soundUseCase.playSound(event.soundUrl)
    }
}