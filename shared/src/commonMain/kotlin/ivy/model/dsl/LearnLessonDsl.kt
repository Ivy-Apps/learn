package ivy.model.dsl

import ivy.model.*

class LessonContentScopeImpl : LessonContentScope {
    private var rootItem: LessonItemId? = null
    private var currentItem: LessonItemId? = null
    private val items = mutableMapOf<LessonItemId, LessonItem>()

    override fun textItem(id: String, builder: TextScope.() -> Unit) {
        val scope = TextScopeImpl().also(builder)
        items[chain(id)] = TextContentItem(
            id = LessonItemId(id),
            text = scope.text,
            style = scope.style,
            next = null,
        )
    }

    override fun question(
        id: String,
        builder: QuestionScope.() -> Unit
    ) {
        val scope = QuestionScopeImpl().also(builder)
        val domainAnswers = scope.answers.mapIndexed { index, it ->
            Answer(
                id = AnswerId("$id-$index"),
                text = it.text,
                explanation = it.explanation,
            ) to it.correct
        }
        items[chain(id)] = QuestionItem(
            id = LessonItemId(id),
            question = scope.question,
            answers = domainAnswers.map { it.first },
            correct = domainAnswers.filter { it.second }
                .map { it.first.id }.toSet(),
            next = null,
        )
    }

    override fun openQuestion(id: String, builder: OpenQuestionScope.() -> Unit) {
        val scope = OpenQuestionScopeImpl().also(builder)
        items[chain(id)] = OpenQuestionItem(
            id = LessonItemId(id),
            question = scope.question,
            correctAnswer = scope.correctAnswer,
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

    override fun choice(
        id: String,
        builder: ChoiceScope.() -> Unit
    ) {
        val scope = ChoiceScopeImpl().also(builder)
        items[chain(id)] = ChoiceItem(
            id = LessonItemId(id),
            question = scope.question,
            options = scope.options.map {
                ChoiceOption(
                    id = ChoiceOptionId(it.text),
                    text = it.text,
                    next = it.next,
                )
            },
        )
    }

    override fun mystery(id: String, builder: MysteryItemScope.() -> Unit) {
        val scope = MysteryItemScopeImpl().also(builder)
        items[chain(id)] = MysteryItem(
            id = LessonItemId(id),
            text = scope.text,
            hidden = scope.hiddenItemId!!,
            next = null,
        )
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

class TextScopeImpl : TextScope {
    var style: TextContentStyle = TextContentStyle.Body
    lateinit var text: String

    override fun style(style: TextContentStyle) {
        this.style = style
    }

    override fun text(text: String) {
        this.text = text
    }

}

class QuestionScopeImpl : QuestionScope {
    lateinit var question: String
    val answers = mutableListOf<AnswerData>()

    override fun questionText(text: String) {
        question = text
    }

    override fun answer(
        text: String,
        explanation: String?,
        correct: Boolean
    ) {
        answers.add(AnswerData(text, explanation, correct))
    }

    data class AnswerData(
        val text: String,
        val explanation: String?,
        val correct: Boolean
    )
}

class OpenQuestionScopeImpl : OpenQuestionScope {
    lateinit var question: String
    lateinit var correctAnswer: String

    override fun question(text: String) {
        question = text
    }

    override fun correctAnswer(text: String) {
        correctAnswer = text
    }
}

class ChoiceScopeImpl : ChoiceScope {
    lateinit var question: String
    val options = mutableListOf<ChoiceData>()

    override fun question(text: String) {
        question = text
    }

    override fun option(text: String, next: LessonItemId) {
        options.add(ChoiceData(text, next))
    }

    data class ChoiceData(
        val text: String,
        val next: LessonItemId
    )
}

class MysteryItemScopeImpl : MysteryItemScope {
    lateinit var text: String
    var hiddenItemId: LessonItemId? = null

    override fun text(text: String) {
        this.text = text
    }

    override fun hiddenItemId(item: LessonItemId) {
        hiddenItemId = item
    }
}