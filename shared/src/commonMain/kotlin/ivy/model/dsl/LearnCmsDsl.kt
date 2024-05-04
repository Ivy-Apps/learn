package ivy.model.dsl

import ivy.model.LessonContent
import ivy.model.LessonItemId
import ivy.model.TextContentStyle

@LearnCmsDsl
fun lessonContent(
    builder: LessonContentScope.() -> Unit
): LessonContent {
    val scope = LessonContentScopeImpl().apply(builder)
    return scope.build()
}

interface LessonContentScope {
    @LearnCmsDsl
    fun textItem(id: String, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LearnCmsDsl
    fun openQuestion(id: String, builder: OpenQuestionScope.() -> Unit)

    @LearnCmsDsl
    fun lessonNavigation(
        id: String,
        text: String,
        onClick: LessonItemId
    )

    @LearnCmsDsl
    fun link(
        id: String,
        text: String,
        url: String
    )

    @LearnCmsDsl
    fun lottie(id: String, jsonUrl: String)

    @LearnCmsDsl
    fun image(id: String, imageUrl: String)

    @LearnCmsDsl
    fun choice(id: String, builder: ChoiceScope.() -> Unit)

    @LearnCmsDsl
    fun mystery(id: String, builder: MysteryItemScope.() -> Unit)

    fun build(): LessonContent
}

interface TextScope {
    @LearnCmsDsl
    fun style(style: TextContentStyle)

    @LearnCmsDsl
    fun text(text: String)
}

interface QuestionScope {
    @LearnCmsDsl
    fun questionText(text: String)

    @LearnCmsDsl
    fun answer(
        text: String, explanation: String? = null, correct: Boolean = false
    )
}

interface OpenQuestionScope {
    @LearnCmsDsl
    fun question(text: String)

    @LearnCmsDsl
    fun correctAnswer(text: String)
}

interface ChoiceScope {
    @LearnCmsDsl
    fun question(text: String)

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

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class LearnCmsDsl