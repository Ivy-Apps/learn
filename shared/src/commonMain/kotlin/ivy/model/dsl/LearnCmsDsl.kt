package ivy.model.dsl

import ivy.model.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@LearnCmsDsl
fun lessonContent(
    builder: LessonContentScope.() -> Unit
): LessonContent {
    val scope = LessonContentScopeImpl().apply(builder)
    return scope.build()
}

fun printLessonJson(lesson: LessonContent) {
    validateIds(lesson)
    println(Json.encodeToString(lesson))
}

private fun validateIds(lesson: LessonContent) {
    allItemsIds(
        currentId = lesson.rootItem,
        lesson = lesson,
    ).forEach { itemId ->
        if (itemId !in lesson.items) {
            error("Item with id $itemId is not defined in the lesson")
        }
    }
}

private fun allItemsIds(
    currentId: LessonItemId?,
    lesson: LessonContent
): Set<LessonItemId> {
    if (currentId == null) return emptySet()
    return setOf(currentId) + when (val currentItem = lesson.items[currentId]) {
        is LessonNavigationItem -> allItemsIds(currentItem.next, lesson) + currentItem.onClick
        is ChoiceItem -> currentItem.options.flatMap { allItemsIds(it.next, lesson) }.toSet()
        is LinearItem -> allItemsIds(currentItem.next, lesson)
        else -> emptySet()
    }
}

interface LessonContentScope {
    @LearnCmsDsl
    fun text(
        id: String,
        next: String? = null,
        builder: TextScope.() -> Unit
    )

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LearnCmsDsl
    fun openQuestion(id: String, builder: OpenQuestionScope.() -> Unit)

    @LearnCmsDsl
    fun lessonNavigation(id: String, builder: LessonNavigationScope.() -> Unit)

    @LearnCmsDsl
    fun link(id: String, builder: LinkScope.() -> Unit)

    @LearnCmsDsl
    fun lottie(id: String, builder: LottieAnimationScope.() -> Unit)

    @LearnCmsDsl
    fun image(id: String, builder: ImageScope.() -> Unit)

    @LearnCmsDsl
    fun choice(id: String, builder: ChoiceScope.() -> Unit)

    @LearnCmsDsl
    fun mystery(id: String, builder: MysteryItemScope.() -> Unit)

    @LearnCmsDsl
    fun sound(id: String, builder: SoundScope.() -> Unit)

    fun build(): LessonContent
}

interface TextScope {
    var style: TextStyle
    var text: String
}

interface QuestionScope {
    var question: String

    @LearnCmsDsl
    fun answer(
        text: String, explanation: String? = null, correct: Boolean = false
    )
}

interface OpenQuestionScope {
    var question: String
    var correctAnswer: String
}

interface ChoiceScope {
    var question: String

    @LearnCmsDsl
    fun option(
        text: String, next: LessonItemId
    )
}

interface MysteryItemScope {
    @LearnCmsDsl
    fun text(text: String)

    @LearnCmsDsl
    fun hiddenItemId(item: LessonItemId)
}

interface LottieAnimationScope {
    var jsonUrl: String
}

interface ImageScope {
    var imageUrl: String
}

interface SoundScope {
    var soundUrl: String
    var buttonText: String
}

interface LessonNavigationScope {
    var text: String
    var onClick: LessonItemId
}

interface LinkScope {
    var text: String
    var url: String
}

@DslMarker
annotation class LearnCmsDsl

@TextBuilderDsl
fun textBuilder(builder: TextBuilderScope.() -> Unit): String {
    val scope = TextBuilder().apply(builder)
    return scope.build()
}

interface TextBuilderScope {
    @TextBuilderDsl
    fun line(text: String)

    fun newLine()
}

class TextBuilder : TextBuilderScope {
    private val lines = mutableListOf<String>()

    override fun line(text: String) {
        lines += text + "\n"
    }

    override fun newLine() {
        lines += ""
    }

    fun build(): String = lines.joinToString("\n")
        .dropLast(1) // the last new-line isn't needed
}

@DslMarker
annotation class TextBuilderDsl

