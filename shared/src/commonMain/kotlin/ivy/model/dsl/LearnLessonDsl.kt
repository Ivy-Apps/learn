package ivy.model.dsl

import ivy.model.*

class LessonContentScopeImpl : LessonContentScope {
    private var rootItem: LessonItemId? = null
    private var currentItemId: LessonItemId? = null
    private val items = mutableMapOf<LessonItemId, LessonItem>()

    override fun text(
        id: String,
        next: String?,
        builder: TextScope.() -> Unit
    ) {
        val scope = TextScopeImpl().also(builder)
        items[chain(id)] = TextItem(
            id = LessonItemId(id),
            text = scope.text,
            style = scope.style,
            next = next?.let(::LessonItemId),
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
            clarification = scope.clarification,
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

    override fun lessonNavigation(id: String, builder: LessonNavigationScope.() -> Unit) {
        val scope = LessonNavigationScopeImpl().also(builder)
        items[chain(id)] = LessonNavigationItem(
            id = LessonItemId(id),
            text = scope.text,
            onClick = scope.onClick,
            next = null,
        )
    }

    override fun link(id: String, builder: LinkScope.() -> Unit) {
        val scope = LinkScopeImpl().also(builder)
        items[chain(id)] = LinkItem(
            id = LessonItemId(id),
            text = scope.text,
            url = scope.url,
            next = null,
        )
    }

    override fun lottie(id: String, builder: LottieAnimationScope.() -> Unit) {
        val scope = LottieAnimationScopeImpl().also(builder)
        items[chain(id)] = LottieAnimationItem(
            id = LessonItemId(id),
            lottie = LottieAnimation(url = scope.jsonUrl),
            next = null,
        )
    }

    override fun image(id: String, builder: ImageScope.() -> Unit) {
        val scope = ImageScopeImpl().also(builder)
        items[chain(id)] = ImageItem(
            id = LessonItemId(id),
            image = ImageUrl(scope.imageUrl),
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

    override fun sound(id: String, builder: SoundScope.() -> Unit) {
        val scope = SoundScopeImpl().also(builder)
        items[chain(id)] = SoundItem(
            id = LessonItemId(id),
            text = scope.buttonText,
            sound = SoundUrl(scope.soundUrl),
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
        } else {
            val currentItem = items[currentItemId!!]!!
            if (currentItem.nextOrNull() == null) {
                currentItem.setNextTo(lessonItemId)
            }
        }
        currentItemId = lessonItemId
        return currentItemId!!
    }

    private fun LessonItem.setNextTo(next: LessonItemId) {
        val updated = when (this) {
            is ChoiceItem -> this
            is ImageItem -> copy(next = next)
            is LessonNavigationItem -> copy(next = next)
            is LinkItem -> copy(next = next)
            is LottieAnimationItem -> copy(next = next)
            is MysteryItem -> copy(next = next)
            is OpenQuestionItem -> copy(next = next)
            is QuestionItem -> copy(next = next)
            is TextItem -> copy(next = next)
            is SoundItem -> copy(next = next)
        }
        items[id] = updated
    }

    private fun LessonItem.nextOrNull(): LessonItemId? {
        return when (this) {
            is LinearItem -> next
            else -> null
        }
    }
}

class TextScopeImpl : TextScope {
    override var style: TextStyle = TextStyle.Body
    override var text: String = ""
}

class QuestionScopeImpl : QuestionScope {
    override var question: String = ""
    override var clarification: String? = null
    val answers = mutableListOf<AnswerData>()

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
    override var question: String = ""
    override var correctAnswer: String = ""
}

class ChoiceScopeImpl : ChoiceScope {
    override var question: String = ""
    val options = mutableListOf<ChoiceData>()

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

class LottieAnimationScopeImpl : LottieAnimationScope {
    override var jsonUrl: String = ""
}

class ImageScopeImpl : ImageScope {
    override var imageUrl: String = ""
}

class LessonNavigationScopeImpl : LessonNavigationScope {
    override var text: String = ""
    override var onClick: LessonItemId = LessonItemId("")
}

class SoundScopeImpl : SoundScope {
    override var soundUrl: String = ""
    override var buttonText: String = ""
}

class LinkScopeImpl : LinkScope {
    override var text: String = ""
    override var url: String = ""
}