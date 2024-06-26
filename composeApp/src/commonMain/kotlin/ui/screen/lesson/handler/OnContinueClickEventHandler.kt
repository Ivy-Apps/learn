package ui.screen.lesson.handler

import Platform
import ivy.content.SoundsUrls
import ui.EventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.completed
import ui.screen.lesson.mapper.toDomain

class OnContinueClickEventHandler(
    private val platform: Platform,
) :
    EventHandler<LessonViewEvent.OnContinueClick, LocalState> {
    override val eventTypes = setOf(LessonViewEvent.OnContinueClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnContinueClick
    ) {
        modifyState { state ->
            LocalState.completed.modify(state) { completed ->
                completed + event.currentItemId.toDomain()
            }
        }
        platform.playSound(SoundsUrls.Complete)
    }
}