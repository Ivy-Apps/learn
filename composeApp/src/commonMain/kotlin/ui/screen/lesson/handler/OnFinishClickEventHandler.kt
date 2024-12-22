package ui.screen.lesson.handler

import data.lesson.LessonRepository
import domain.SoundUseCase
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.content.SoundsUrls
import navigation.Navigation
import ui.screen.lesson.LessonEventHandler
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonVmContext
import util.Logger

class OnFinishClickEventHandler(
    private val navigation: Navigation,
    private val soundUseCase: SoundUseCase,
    private val lessonRepository: LessonRepository,
    private val logger: Logger,
    private val analytics: Analytics,
) : LessonEventHandler<LessonViewEvent.OnFinishClick> {
    override val eventTypes = setOf(LessonViewEvent.OnFinishClick::class)

    override suspend fun LessonVmContext.handleEvent(
        event: LessonViewEvent.OnFinishClick
    ) {
        navigation.navigateBack()
        soundUseCase.playSound(SoundsUrls.CompleteLesson)
        lessonRepository.markLessonAsCompleted(
            course = args.courseId,
            lesson = args.lessonId,
        ).onLeft { errMsg ->
            logger.error("Failed to mark lesson as completed because: $errMsg")
        }
        analytics.logEvent(
            source = Source.Lesson,
            event = "complete"
        )
    }
}