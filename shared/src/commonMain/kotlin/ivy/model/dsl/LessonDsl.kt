package ivy.model.dsl

import ivy.model.*

@LessonDsl
fun lesson(
    id: LessonId,
    name: String,
    tagline: String,
    build: LessonScope.() -> Unit
): Lesson {
    return Lesson(
        id = id,
        name = name,
        tagline = tagline,
        rootItem = LessonItemId(value = ""),
        items = mapOf()
    )
}

class LessonScopeImpl : LessonScope {
    private var rootItem: LessonItemId? = null
    private var currentItem: LessonItemId? = null
    private val items = mutableMapOf<LessonItemId, LessonItem>()

    override fun body(id: String, text: String) {
        items[chain(id)] = TextContentItem(
            id = LessonItemId(id),
            text = text,
            style = TextContentStyle.Heading,
            next = null,
        )
    }

    override fun heading(id: String, text: String) {
        items[chain(id)] = TextContentItem(
            id = LessonItemId(id),
            text = text,
            style = TextContentStyle.Body,
            next = null,
        )
    }

    override fun question(id: String, text: String, answers: List<AnswerDsl>) {
        val domainAnswers = answers.mapIndexed { index, it ->
            Answer(
                id = AnswerId("$id-$index"),
                text = it.text,
                explanation = it.explanation,
            ) to it.correct
        }
        items[chain(id)] = QuestionItem(
            id = LessonItemId(id),
            question = text,
            answers = domainAnswers.map { it.first },
            correct = domainAnswers.filter { it.second }.map { it.first.id }.toSet(),
            next = null,
        )
    }

    override fun openQuestion(id: String, text: String, correctAnswer: String) {
        items[chain(id)] = OpenQuestionItem(
            id = LessonItemId(id),
            question = text,
            correctAnswer = correctAnswer,
            next = null,
        )
    }

    override fun lessonNavigation(id: String, text: String, onClick: LessonItemId) {
        items[chain(id)] = LessonNavigationItem(
            id = LessonItemId(id),
            text = text,
            onClick = onClick,
            next = null,
        )
    }

    override fun link(id: String, text: String, url: String) {
        items[chain(id)] = LinkItem(
            id = LessonItemId(id),
            text = text,
            url = url,
            next = null,
        )
    }

    override fun lottie(id: String, jsonUrl: String) {
        items[chain(id)] = LottieAnimationItem(
            id = LessonItemId(id),
            lottie = LottieAnimation(url = jsonUrl),
            next = null,
        )
    }

    override fun image(id: String, imageUrl: String) {
        items[chain(id)] = ImageItem(
            id = LessonItemId(id),
            image = ImageUrl(imageUrl),
            next = null,
        )
    }

    override fun choice(id: String, question: String, options: List<ChoiceDsl>) {
        TODO("Not yet implemented")
    }

    override fun build(): LessonContent = LessonContent(
        rootItem = rootItem!!,
        items = items
    )

    private fun chain(id: String): LessonItemId {
        val lessonItemId = LessonItemId(id)
        if (rootItem == null) {
            rootItem = lessonItemId
            currentItem = rootItem
        } else {
            items[currentItem!!]!!.chainTo(lessonItemId)
            currentItem = lessonItemId
        }
        return currentItem!!
    }

    private fun LessonItem.chainTo(next: LessonItemId) {
        val updated = when (this) {
            is ChoiceItem -> this
            is ImageItem -> copy(next = next)
            is LessonNavigationItem -> copy(next = next)
            is LinkItem -> copy(next = next)
            is LottieAnimationItem -> copy(next = next)
            is MysteryItem -> copy(next = next)
            is OpenQuestionItem -> copy(next = next)
            is QuestionItem -> copy(next = next)
            is TextContentItem -> copy(next = next)
        }
        items[id] = updated
    }

}

interface LessonScope {
    @LessonDsl
    fun body(
        id: String,
        text: String
    )

    @LessonDsl
    fun heading(
        id: String,
        text: String
    )

    @LessonDsl
    fun question(
        id: String,
        text: String,
        answers: List<AnswerDsl>
    )

    @LessonDsl
    fun openQuestion(
        id: String,
        text: String,
        correctAnswer: String,
    )

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
    fun lottie(
        id: String,
        jsonUrl: String
    )

    @LessonDsl
    fun image(
        id: String,
        imageUrl: String
    )

    @LessonDsl
    fun choice(
        id: String,
        question: String,
        options: List<ChoiceDsl>
    )

    fun build(): LessonContent
}

data class LessonContent(
    val rootItem: LessonItemId,
    val items: Map<LessonItemId, LessonItem>,
)

data class AnswerDsl(
    val text: String,
    val explanation: String? = null,
    val correct: Boolean = false
)

data class ChoiceDsl(
    val text: String,
    val next: LessonItemId,
)

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class LessonDsl

fun test() = lesson(
    id = LessonId("what_is_programming"),
    name = "What is programming?",
    tagline = "Learn the basics of programming."
) {
    body(
        "1",
        "Programming is the process of creating a set of instructions that tell a computer how to perform a task."
    )
}