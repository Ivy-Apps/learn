package ivy.model

import kotlin.jvm.JvmInline

data class Lesson(
    val name: String,
    val tagline: String,
    val rootItem: LessonItemId,
    val items: Map<LessonItemId, LessonItem>,
    /**
     * % in [0, 1]
     */
    val percentCompleted: Float
)

sealed interface LessonItem {
    val id: LessonItemId
}

@JvmInline
value class LessonItemId(val value: String)

/**
 * Item that doesn't branch the tree.
 */
interface LinearItem {
    val next: LessonItemId?
}

data class TextContentItem(
    override val id: LessonItemId,
    val style: TextContentStyle,
    val text: String,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

enum class TextContentStyle {
    Title, MultilineText
}

data class ChoiceQuestionItem(
    override val id: LessonItemId,
    val question: String,
    val answers: List<Answer>,
    val correct: Set<AnswerId>,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

data class Answer(
    val id: AnswerId,
    val text: String,
)

@JvmInline
value class AnswerId(val value: String)

data class OpenQuestionItem(
    override val id: LessonItemId,
    val question: String,
    val correctAnswer: String,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

data class LinkItem(
    override val id: LessonItemId,
    val text: String,
    val url: String,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

data class LessonNavigationItem(
    override val id: LessonItemId,
    val text: String,
    val onClick: LessonItemId,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

data class LottieAnimationItem(
    override val id: LessonItemId,
    val lottie: LottieAnimation,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@JvmInline
value class LottieAnimation(val url: String)

data class ImageItem(
    override val id: LessonItemId,
    val image: ImageUrl,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@JvmInline
value class ImageUrl(val url: String)

data class ChoiceItem(
    override val id: LessonItemId,
    val question: String,
    val options: ChoiceOption,
) : LessonItem

data class ChoiceOption(
    val id: ChoiceOptionId,
    val text: String,
    val next: LessonItemId,
)

@JvmInline
value class ChoiceOptionId(val value: String)

data class MysteryItem(
    override val id: LessonItemId,
    val text: String,
    val hidden: LessonItemId,
    override val next: LessonItemId?
) : LessonItem, LinearItem