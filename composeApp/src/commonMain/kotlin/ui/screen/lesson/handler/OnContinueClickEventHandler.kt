package ui.screen.lesson.handler

import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.mapper.toDomain

class OnContinueClickEventHandler :
    EventHandler<LessonViewEvent.OnContinueClick, LessonViewModel.LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnContinueClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnContinueClick
    ) {
        modify {
            it.copy(
                completed = it.completed + event.currentItemId.toDomain(),
            )
        }
    }
}