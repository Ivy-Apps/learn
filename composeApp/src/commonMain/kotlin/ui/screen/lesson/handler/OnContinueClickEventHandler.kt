package ui.screen.lesson.handler

import domain.SoundUseCase
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.content.SoundsUrls
import ivy.model.analytics.*
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.completed
import ui.screen.lesson.mapper.toDomain

class OnContinueClickEventHandler(
  private val soundUseCase: SoundUseCase,
  private val analytics: Analytics,
) : LessonEventHandler<LessonViewEvent.OnContinueClick> {
  override suspend fun LessonVmContext.handleEvent(
    event: LessonViewEvent.OnContinueClick
  ) {
    modifyState { state ->
      LocalState.completed.modify(state) { completed ->
        completed + event.currentItemId.toDomain()
      }
    }
    soundUseCase.playSound(SoundsUrls.Complete)
    analytics.logEvent(
      source = Source.Lesson,
      event = "click_continue",
      params = params {
        courseId(args.courseId)
        lessonId(args.lessonId)
        lessonName(args.lessonName)
        itemId(event.currentItemId.value)
      }
    )
  }
}