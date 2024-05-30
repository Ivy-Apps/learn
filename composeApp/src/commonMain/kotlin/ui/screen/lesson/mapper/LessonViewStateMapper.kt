package ui.screen.lesson.mapper

import ivy.model.Lesson
import kotlinx.collections.immutable.persistentListOf
import ui.screen.lesson.LessonViewState

class LessonViewStateMapper {
    fun Lesson.toViewState(): LessonViewState = LessonViewState(
        title = name,
        items = persistentListOf()
    )
}