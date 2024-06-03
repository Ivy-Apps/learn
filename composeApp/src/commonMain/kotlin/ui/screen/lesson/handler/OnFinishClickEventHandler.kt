package ui.screen.lesson.handler

import Platform
import ivy.content.SoundsUrls
import ui.EventHandler
import ui.navigation.Navigation
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext

class OnFinishClickEventHandler(
    private val platform: Platform,
    private val navigation: Navigation,
) :
    EventHandler<LessonViewEvent.OnFinishClick, LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnFinishClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnFinishClick
    ) {
        navigation.back()
        platform.playSound(SoundsUrls.CompleteLesson)
    }
}