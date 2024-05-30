package ui.screen.lesson.mapper

import ivy.model.*
import ui.screen.lesson.LessonViewModel

class LessonTreeManager {

    fun loadUserProgress(
        lesson: LessonContent,
        localState: LessonViewModel.LocalState
    ): List<LessonItem> = load(
        lesson = lesson,
        localState = localState,
        currentItemId = lesson.rootItem,
    )

    private fun load(
        lesson: LessonContent,
        localState: LessonViewModel.LocalState,
        currentItemId: LessonItemId,
        autoLoadedTimes: Int = 0,
    ): List<LessonItem> {
        val currentItem = lesson.items[currentItemId] ?: return emptyList()
        val nextItemId = currentItem.nextItemId(localState)
        return listOf(currentItem) + nextItemId?.takeIf {
            when (currentItem) {
                is QuestionItem,
                is OpenQuestionItem,
                is ChoiceItem -> {
                    currentItemId in localState.answered
                }

                else -> {
                    autoLoadedTimes < 1
                }
            }
        }?.let {
            load(
                lesson = lesson,
                localState = localState,
                currentItemId = nextItemId,
                autoLoadedTimes = autoLoadedTimes + 1,
            )
        }.orEmpty()
    }


    private fun LessonItem.nextItemId(
        localState: LessonViewModel.LocalState
    ): LessonItemId? = when (this) {
        is LinearItem -> next
        is ChoiceItem -> localState.choices[this.id]?.let { choiceId ->
            options.firstOrNull { it.id == choiceId }?.next
        }

        else -> null
    }
}