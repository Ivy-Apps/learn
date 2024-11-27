package ui.screen.lesson.handler

import navigation.Navigation
import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel
import ui.screen.lesson.LessonVmContext

class OnBackClickEventHandler(
    private val navigation: Navigation
) : EventHandler<LessonViewEvent.OnBackClick, LessonViewModel.LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnBackClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnBackClick
    ) {
        navigation.navigateBack()
    }
}