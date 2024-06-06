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
    validateIdsExistence(lesson)
    validateIdsUniqueness(lesson)
    println(Json.encodeToString(lesson))
}

fun story(
    lesson: LessonContent,
    choices: Map<LessonItemId, ChoiceOptionId> = emptyMap(),
    currentItemId: LessonItemId? = lesson.rootItem,
): String = if (currentItemId != null) {
    when (val item = lesson.items[currentItemId]!!) {
        is TextItem -> {
            when (item.style) {
                TextStyle.Heading -> "\n# ${item.text}\n\n"
                else -> item.text
            } + story(lesson, choices, item.next)
        }

        is ChoiceItem -> {
            val option = item.options.firstOrNull {
                choices[currentItemId] == it.id
            } ?: item.options.first()
            story(lesson, choices, option.next)
        }

        is LinearItem -> story(lesson, choices, item.next)
        else -> ""
    }
} else {
    ""
}

private fun validateIdsExistence(lesson: LessonContent) {
    allItemsIds(
        currentId = lesson.rootItem,
        lesson = lesson,
    ).forEach { itemId ->
        if (itemId !in lesson.items) {
            error("Item with id $itemId is not defined in the lesson")
        }
    }
}

private fun validateIdsUniqueness(lesson: LessonContent) {
    val processedIds = mutableSetOf<LessonItemId>()
    lesson.items.values.forEach { item ->
        if (item.id in processedIds) {
            error("Item with id '${item.id}' is defined more than once")
        }
        processedIds += item.id
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
    var clarification: String?

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

    @TextBuilderDsl
    fun bullet(text: String)

    @TextBuilderDsl
    fun newLine()
}

class TextBuilder : TextBuilderScope {
    private val items = mutableListOf<Item>()

    override fun line(text: String) {
        items += Item.Line(text)
    }

    override fun newLine() {
        items += Item.NewLine
    }

    override fun bullet(text: String) {
        items += Item.Bullet(text)
    }

    fun build(): String = buildString {
        for (item in items) {
            when (item) {
                is Item.Line -> {
                    append(item.text + "\n")
                    append("\n")
                }

                is Item.NewLine -> append("\n")
                is Item.Bullet -> append("• ${item.text}\n")
            }
        }
    }.dropLast(1) // the last new-line isn't needed

    sealed interface Item {
        data class Line(val text: String) : Item
        data object NewLine : Item
        data class Bullet(val text: String) : Item
    }
}

@DslMarker
annotation class TextBuilderDsl

fun codeBuilder(builder: CodeBuilderScope.() -> Unit): String {
    val scope = CodeBuilder().apply(builder)
    return scope.build()
}

interface CodeBuilderScope {
    @CodeBuilderDsl
    fun line(text: String)
}

class CodeBuilder : CodeBuilderScope {
    private val lines = mutableListOf<String>()

    override fun line(text: String) {
        lines += text
    }

    fun build(): String = lines.joinToString("\n")
}

@DslMarker
annotation class CodeBuilderDsl

