package ui.screen.lesson.handler

import domain.SoundUseCase
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.content.SoundsUrls
import ivy.model.analytics.*
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewModel.LocalState
import ui.screen.lesson.LessonVmContext
import ui.screen.lesson.QuestionViewEvent
import ui.screen.lesson.answered
import ui.screen.lesson.mapper.toDomain

class OnCheckClickEventHandler(
  private val soundUseCase: SoundUseCase,
  private val analytics: Analytics,
) : LessonEventHandler<QuestionViewEvent.OnCheckClick> {

  override suspend fun LessonVmContext.handleEvent(
    event: QuestionViewEvent.OnCheckClick
  ) {
    modifyState { state ->
      LocalState.answered.modify(state) { completed ->
        completed + event.questionId.toDomain()
      }
    }
    val correctAnswer = event.answers.all {
      it.selected == it.correct
    }
    soundUseCase.playSound(
      sound = if (correctAnswer) {
        SoundsUrls.Success
      } else {
        SoundsUrls.Ups
      }
    )
    analytics.logEvent(
      source = Source.Lesson,
      event = if (correctAnswer) {
        "answer_correctly"
      } else {
        "answer_incorrectly"
      },
      params = params {
        courseId(args.courseId)
        lessonId(args.lessonId)
        lessonName(args.lessonName)
        questionId(event.questionId.value)
        answers(
          event.answers.filter { it.selected }
            .joinToString(separator = ", ") { it.text }
        )
      }
    )
  }

}