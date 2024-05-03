package ivy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Lesson(
    val id: LessonId,
    val name: String,
    val tagline: String,
    val rootItem: LessonItemId,
    val items: Map<LessonItemId, LessonItem>,
)

@Serializable
@JvmInline
value class LessonId(val value: String)

@Serializable
sealed interface LessonItem {
    val id: LessonItemId
}

@Serializable
@JvmInline
value class LessonItemId(val value: String)

/**
 * Item that doesn't branch the tree.
 */
interface LinearItem {
    val next: LessonItemId?
}

@Serializable
@SerialName("TextContentItem")
data class TextContentItem(
    override val id: LessonItemId,
    val text: String,
    val style: TextContentStyle,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
enum class TextContentStyle {
    Heading, Body
}

@Serializable
@SerialName("QuestionItem")
data class QuestionItem(
    override val id: LessonItemId,
    val question: String,
    val answers: List<Answer>,
    val correct: Set<AnswerId>,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
data class Answer(
    val id: AnswerId,
    val text: String,
    val explanation: String?,
)

@Serializable
@JvmInline
value class AnswerId(val value: String)

@Serializable
@SerialName("OpenQuestionItem")
data class OpenQuestionItem(
    override val id: LessonItemId,
    val question: String,
    val correctAnswer: String,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LinkItem")
data class LinkItem(
    override val id: LessonItemId,
    val text: String,
    val url: String,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LessonNavigationItem")
data class LessonNavigationItem(
    override val id: LessonItemId,
    val text: String,
    val onClick: LessonItemId,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LottieAnimationItem")
data class LottieAnimationItem(
    override val id: LessonItemId,
    val lottie: LottieAnimation,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@JvmInline
value class LottieAnimation(val url: String)

@Serializable
@SerialName("ImageItem")
data class ImageItem(
    override val id: LessonItemId,
    val image: ImageUrl,
    override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@JvmInline
value class ImageUrl(val url: String)

@Serializable
@SerialName("ChoiceItem")
data class ChoiceItem(
    override val id: LessonItemId,
    val question: String,
    val options: List<ChoiceOption>,
) : LessonItem

@Serializable
data class ChoiceOption(
    val id: ChoiceOptionId,
    val text: String,
    val next: LessonItemId,
)

@Serializable
@JvmInline
value class ChoiceOptionId(val value: String)

@Serializable
@SerialName("MysteryItem")
data class MysteryItem(
    override val id: LessonItemId,
    val text: String,
    val hidden: LessonItemId,
    override val next: LessonItemId?
) : LessonItem, LinearItem