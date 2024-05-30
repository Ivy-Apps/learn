package ui.screen.lesson.mapper

import ivy.model.*
import ivy.model.TextContentStyle.Body
import ivy.model.TextContentStyle.Heading
import kotlinx.collections.immutable.toImmutableList
import ui.screen.lesson.*

class LessonViewStateMapper(
    private val lessonTreeManager: LessonTreeManager
) {
    fun Lesson.toViewState(
        localState: LessonViewModel.LocalState,
    ): LessonViewState {
        return LessonViewState(
            title = name,
            items = lessonTreeManager.loadUserProgress(
                lesson = content,
                localState = localState
            ).mapNotNull {
                it.toViewState(localState, content.items)
            }.toImmutableList()
        )
    }

    private fun LessonItem.toViewState(
        localState: LessonViewModel.LocalState,
        items: Map<LessonItemId, LessonItem>
    ): LessonItemViewState? = when (this) {
        is ChoiceItem -> toViewState()
        is ImageItem -> toViewState()
        is LessonNavigationItem -> toViewState()
        is LinkItem -> toViewState()
        is LottieAnimationItem -> toViewState()
        is MysteryItem -> toViewState(localState, items)
        is OpenQuestionItem -> toViewState(localState)
        is QuestionItem -> toViewState(localState)
        is TextContentItem -> toViewState()
    }

    private fun ChoiceItem.toViewState(): ChoiceItemViewState = ChoiceItemViewState(
        id = id.toViewState(),
        question = question,
        options = options.map { it.toViewState() }
    )

    private fun ChoiceOption.toViewState(): ChoiceOptionViewState = ChoiceOptionViewState(
        id = id.value,
        text = text
    )

    private fun ImageItem.toViewState(): ImageItemViewState = ImageItemViewState(
        id = id.toViewState(),
        imageUrl = image.url
    )

    private fun LessonNavigationItem.toViewState(): LessonNavigationItemViewState = LessonNavigationItemViewState(
        id = id.toViewState(),
        text = text,
        navigateToItemId = onClick.toViewState(),
    )

    private fun LinkItem.toViewState(): LinkItemViewState = LinkItemViewState(
        id = id.toViewState(),
        text = text,
        url = url,
    )

    private fun LottieAnimationItem.toViewState(): LottieAnimationItemViewState = LottieAnimationItemViewState(
        id = id.toViewState(),
        lottieUrl = lottie.url,
    )

    private fun MysteryItem.toViewState(
        localState: LessonViewModel.LocalState,
        items: Map<LessonItemId, LessonItem>
    ): MysteryItemViewState? {
        return MysteryItemViewState(
            id = id.toViewState(),
            text = text,
            hidden = items[hidden]?.toViewState(localState, items) ?: return null,
        )
    }

    private fun OpenQuestionItem.toViewState(
        localState: LessonViewModel.LocalState
    ): OpenQuestionItemViewState = OpenQuestionItemViewState(
        id = id.toViewState(),
        question = question,
        answer = localState.openAnswers[id],
        correctAnswer = correctAnswer,
        answered = id in localState.answered,
    )

    private fun QuestionItem.toViewState(
        localState: LessonViewModel.LocalState,
    ): QuestionItemViewState = QuestionItemViewState(
        id = id.toViewState(),
        question = question,
        type = if (correct.size == 1) QuestionType.SingleChoice else QuestionType.MultipleChoice,
        answers = answers.map { it.toViewState(this, localState) }
            .toImmutableList(),
        answered = id in localState.answered,
    )

    private fun Answer.toViewState(
        question: QuestionItem,
        localState: LessonViewModel.LocalState,
    ): AnswerViewState = AnswerViewState(
        id = id.value,
        text = text,
        explanation = explanation,
        correct = id in question.correct,
        selected = id in localState.answers.getOrElse(question.id) { emptySet() },
    )

    private fun TextContentItem.toViewState(): TextItemViewState = TextItemViewState(
        id = id.toViewState(),
        text = text,
        style = when (style) {
            Heading -> TextStyleViewState.Heading
            Body -> TextStyleViewState.Body
        }
    )
}

fun LessonItemId.toViewState(): LessonItemIdViewState = LessonItemIdViewState(value)
fun LessonItemIdViewState.toDomain(): LessonItemId = LessonItemId(value)
