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
        autoLoadNextN = 1
    )

    private fun load(
        lesson: LessonContent,
        localState: LessonViewModel.LocalState,
        currentItemId: LessonItemId,
        autoLoadNextN: Int,
    ): List<LessonItem> {
        val currentItem = lesson.items[currentItemId] ?: return emptyList()
        val nextItemId = currentItem.nextItemId(localState)
        return listOf(currentItem) + nextItemId?.takeIf {
            when (currentItem) {
                is QuestionItem,
                is OpenQuestionItem,
                is ChoiceItem -> {
                    currentItemId in localState.completed
                }

                is SoundItem, is LottieAnimationItem -> true

                else -> {
                    currentItemId in localState.completed || autoLoadNextN > 0
                }
            }
        }?.let {
            load(
                lesson = lesson,
                localState = localState,
                currentItemId = nextItemId,
                autoLoadNextN = if (currentItemId in localState.completed) {
                    autoLoadNextN
                } else {
                    autoLoadNextN - 1
                },
            )
        }.orEmpty()
    }


    private fun LessonItem.nextItemId(
        localState: LessonViewModel.LocalState
    ): LessonItemId? = when (this) {
        is LinearItem -> next
        is ChoiceItem -> localState.choices[id]?.let { choiceId ->
            options.firstOrNull { it.id == choiceId }?.next
        }

        else -> null
    }
}