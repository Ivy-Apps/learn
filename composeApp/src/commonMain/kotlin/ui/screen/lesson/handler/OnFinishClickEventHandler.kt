package ui.screen.lesson.handler

import domain.SoundUseCase
import ivy.content.SoundsUrls
import navigation.Navigation
import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext

class OnFinishClickEventHandler(
    private val navigation: Navigation,
    private val soundUseCase: SoundUseCase
) :
    EventHandler<LessonViewEvent.OnFinishClick, LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnFinishClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnFinishClick
    ) {
        navigation.navigateBack()
        soundUseCase.playSound(SoundsUrls.CompleteLesson)
    }
}