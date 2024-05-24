package ui.screen.lesson

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlin.jvm.JvmInline

@Immutable
data class LessonViewState(
    val title: String,
    val items: ImmutableList<LessonItemViewState>
)

@Immutable
sealed interface LessonItemViewState {
    val id: LessonItemIdViewState
}

@Immutable
data class TextItemViewState(
    override val id: LessonItemIdViewState,
    val text: String,
    val style: TextStyleViewState
) : LessonItemViewState

@Immutable
enum class TextStyleViewState {
    Heading, Body
}

@Immutable
data class QuestionItemViewState(
    override val id: LessonItemIdViewState,
    val question: String,
    val type: QuestionType,
    val answers: ImmutableList<AnswerViewState>,
    val answered: Boolean
) : LessonItemViewState

@Immutable
enum class QuestionType {
    SingleChoice,
    MultipleChoice
}

@Immutable
data class AnswerViewState(
    val id: String,
    val text: String,
    val explanation: String?,
    val correct: Boolean,
    val selected: Boolean
)

@Immutable
data class OpenQuestionItemViewState(
    override val id: LessonItemIdViewState,
    val question: String,
    val answer: String?,
    val correctAnswer: String
) : LessonItemViewState

@Immutable
data class LinkItemViewState(
    override val id: LessonItemIdViewState,
    val text: String,
    val url: String
) : LessonItemViewState

@Immutable
data class LessonNavigationItemViewState(
    override val id: LessonItemIdViewState,
    val text: String,
    val navigateToItemId: LessonItemIdViewState
) : LessonItemViewState

@Immutable
data class LottieAnimationItemViewState(
    override val id: LessonItemIdViewState,
    val lottieUrl: String
) : LessonItemViewState

@Immutable
data class ImageItemViewState(
    override val id: LessonItemIdViewState,
    val imageUrl: String
) : LessonItemViewState

@Immutable
data class ChoiceItemViewState(
    override val id: LessonItemIdViewState,
    val question: String,
    val options: List<ChoiceOptionViewState>,
) : LessonItemViewState

@Immutable
data class ChoiceOptionViewState(
    val id: String,
    val text: String
)

@Immutable
data class MysteryItemViewState(
    override val id: LessonItemIdViewState,
    val text: String,
    val hidden: LessonItemViewState
) : LessonItemViewState

@JvmInline
@Immutable
value class LessonItemIdViewState(val value: String)

sealed interface LessonViewEvent {
    data object OnBackClick : LessonViewEvent
    data class OnCheckQuestionClick(val id: LessonItemIdViewState) : LessonViewEvent
    data class OnAnswerCheckChange(
        val questionId: LessonItemIdViewState,
        val answerId: String,
        val checked: Boolean
    ) : LessonViewEvent
}