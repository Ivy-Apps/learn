package ui.screen.lesson.handler

import navigation.Navigation
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonVmContext

class OnBackClickEventHandler(
    private val navigation: Navigation
) : LessonEventHandler<LessonViewEvent.OnBackClick> {
    override val eventTypes = setOf(LessonViewEvent.OnBackClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnBackClick
    ) {
        navigation.navigateBack()
    }
}