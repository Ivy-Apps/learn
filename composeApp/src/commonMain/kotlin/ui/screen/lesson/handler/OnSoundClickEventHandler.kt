package ui.screen.lesson.handler

import domain.SoundUseCase
import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel
import ui.screen.lesson.LessonVmContext

class OnSoundClickEventHandler(
    private val soundUseCase: SoundUseCase
) : EventHandler<LessonViewEvent.OnSoundClick, LessonViewModel.LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnSoundClick::class)

    override suspend fun LessonVmContext.handleEvent(event: LessonViewEvent.OnSoundClick) {
        soundUseCase.playSound(event.soundUrl)
    }
}