package ivy.model.dsl

import ivy.model.Lesson
import ivy.model.LessonId
import ivy.model.LessonItemId
import ivy.model.TextContentStyle

@LessonDsl
fun lesson(
    id: LessonId,
    name: String,
    tagline: String, builder: LessonScope.() -> Unit
): Lesson {
    val scope = LessonScopeImpl()
    scope.builder()
    val content = scope.build()
    return Lesson(
        id = id,
        name = name,
        tagline = tagline,
        rootItem = content.rootItem,
        items = content.items,
    )
}

interface LessonScope {
    @LessonDsl
    fun textItem(id: String, builder: TextScope.() -> Unit)

    @LessonDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LessonDsl
    fun openQuestion(id: String, builder: OpenQuestionScope.() -> Unit)

    @LessonDsl
    fun lessonNavigation(
        id: String,
        text: String,
        onClick: LessonItemId
    )

    @LessonDsl
    fun link(
        id: String,
        text: String,
        url: String
    )

    @LessonDsl
    fun lottie(id: String, jsonUrl: String)

    @LessonDsl
    fun image(id: String, imageUrl: String)

    @LessonDsl
    fun choice(id: String, builder: ChoiceScope.() -> Unit)

    @LessonDsl
    fun mystery(id: String, builder: MysteryItemScope.() -> Unit)

    fun build(): LessonContent
}

interface TextScope {
    @LessonDsl
    fun style(style: TextContentStyle)

    @LessonDsl
    fun text(text: String)
}

interface QuestionScope {
    @LessonDsl
    fun questionText(text: String)

    @LessonDsl
    fun answer(
        text: String, explanation: String? = null, correct: Boolean = false
    )
}

interface OpenQuestionScope {
    @LessonDsl
    fun question(text: String)

    @LessonDsl
    fun correctAnswer(text: String)
}

interface ChoiceScope {
    @LessonDsl
    fun question(text: String)

    @LessonDsl
    fun option(
        text: String, next: LessonItemId
    )
}

interface MysteryItemScope {
    @LessonDsl
    fun text(text: String)

    @LessonDsl
    fun hiddenItemId(item: LessonItemId)
}

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class LessonDsl