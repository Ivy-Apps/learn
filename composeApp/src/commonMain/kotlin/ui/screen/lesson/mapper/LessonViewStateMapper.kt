package ui.screen.lesson.mapper

import LogLevel
import Platform
import ivy.learn.*
import kotlinx.collections.immutable.toImmutableList
import ui.screen.lesson.*

class LessonViewStateMapper(
  private val lessonTreeManager: LessonTreeManager,
  private val platform: Platform,
) {
  private var prevItemsCount = 0

  fun Lesson.toViewState(
    localState: LessonViewModel.LocalState,
  ): LessonViewState {
    val lessonItems = lessonTreeManager.loadUserProgress(
      lesson = content,
      localState = localState
    )
    platform.log(LogLevel.Debug, "Local state: $localState")
    return LessonViewState(
      title = name,
      items = lessonItems.mapNotNull {
        it.toViewState(localState, content.items)
      }.toImmutableList(),
      cta = when (val currentItem = lessonItems.lastOrNull()) {
        null, is OpenQuestionItem,
        is ChoiceItem -> null

        is QuestionItem -> {
          if (currentItem.id in localState.answered) {
            ctaViewState(currentItem)
          } else {
            null
          }
        }

        else -> ctaViewState(currentItem)
      },
      progress = toProgressViewState(lessonItems),
      itemsLoadedDiff = lessonItems.size - prevItemsCount,
    ).also {
      prevItemsCount = lessonItems.size
    }
  }

  private fun ctaViewState(currentItem: LessonItem): CtaViewState {
    return if (currentItem is LinearItem && currentItem.next == null) {
      CtaViewState.Finish(currentItem.id.toViewState())
    } else {
      CtaViewState.Continue(currentItem.id.toViewState())
    }
  }

  private fun Lesson.toProgressViewState(
    lessonItems: List<LessonItem>,
  ): LessonProgressViewState {
    val done = lessonItems.size
    val unreachablePaths = content.items.values.sumOf {
      if (it is ChoiceItem) (it.options.size - 1).coerceAtLeast(0) else 0
    }
    val total = (content.items.size - unreachablePaths - 1) // -1 for the finish item
      .coerceAtLeast(0)
    platform.log(LogLevel.Debug, "done = $done, $unreachablePaths = $unreachablePaths, total = $total")
    return LessonProgressViewState(done, total)
  }

  private fun LessonItem.toViewState(
    localState: LessonViewModel.LocalState,
    items: Map<LessonItemId, LessonItem>
  ): LessonItemViewState? = when (this) {
    is ChoiceItem -> toViewState()
    is ImageItem -> toViewState()
    is LessonNavigationItem -> toViewState()
    is LinkItem -> toViewState()
    is LottieAnimationItem -> toViewState()
    is MysteryItem -> toViewState(localState, items)
    is OpenQuestionItem -> toViewState(localState)
    is QuestionItem -> toViewState(localState)
    is TextItem -> toViewState()
    is CodeItem -> toViewState()
    is SoundItem -> toViewState()
  }

  private fun ChoiceItem.toViewState(): ChoiceItemViewState = ChoiceItemViewState(
    id = id.toViewState(),
    question = question,
    options = options.map { it.toViewState() }.toImmutableList(),
  )

  private fun ChoiceOption.toViewState(): ChoiceOptionViewState = ChoiceOptionViewState(
    id = id.value,
    text = text
  )

  private fun ImageItem.toViewState(): ImageItemViewState = ImageItemViewState(
    id = id.toViewState(),
    imageUrl = image.url
  )

  private fun LessonNavigationItem.toViewState(): LessonNavigationItemViewState = LessonNavigationItemViewState(
    id = id.toViewState(),
    text = text,
    navigateToItemId = onClick.toViewState(),
  )

  private fun LinkItem.toViewState(): LinkItemViewState = LinkItemViewState(
    id = id.toViewState(),
    text = text,
    url = url,
  )

  private fun LottieAnimationItem.toViewState(): LottieAnimationItemViewState = LottieAnimationItemViewState(
    id = id.toViewState(),
    lottieUrl = lottie.url,
  )

  private fun MysteryItem.toViewState(
    localState: LessonViewModel.LocalState,
    items: Map<LessonItemId, LessonItem>
  ): MysteryItemViewState? {
    return MysteryItemViewState(
      id = id.toViewState(),
      text = text,
      hidden = items[hidden]?.toViewState(localState, items) ?: return null,
    )
  }

  private fun OpenQuestionItem.toViewState(
    localState: LessonViewModel.LocalState
  ): OpenQuestionItemViewState = OpenQuestionItemViewState(
    id = id.toViewState(),
    question = question,
    answer = localState.openAnswersInput[id],
    correctAnswer = correctAnswer,
    answered = id in localState.completed,
  )

  private fun QuestionItem.toViewState(
    localState: LessonViewModel.LocalState,
  ): QuestionItemViewState = QuestionItemViewState(
    id = id.toViewState(),
    question = question,
    clarification = clarification,
    type = if (correct.size == 1) QuestionTypeViewState.SingleChoice else QuestionTypeViewState.MultipleChoice,
    answers = answers.map { it.toViewState(this, localState) }
      .shuffled()
      .toImmutableList(),
    answered = id in localState.answered,
  )

  private fun Answer.toViewState(
    question: QuestionItem,
    localState: LessonViewModel.LocalState,
  ): AnswerViewState = AnswerViewState(
    id = id.value,
    text = text,
    explanation = explanation,
    correct = id in question.correct,
    selected = id in localState.selectedAnswers.getOrElse(question.id) { emptySet() },
  )

  private fun TextItem.toViewState(): TextItemViewState = TextItemViewState(
    id = id.toViewState(),
    text = text,
    style = when (style) {
      TextStyle.Heading -> TextStyleViewState.Heading
      TextStyle.Body -> TextStyleViewState.Body
      TextStyle.BodySpacingMedium -> TextStyleViewState.BodyMediumSpacing
      TextStyle.BodySpacingLarge -> TextStyleViewState.BodyBigSpacing
    }
  )

  private fun CodeItem.toViewState(): CodeItemViewState = CodeItemViewState(
    id = id.toViewState(),
    code = code,
  )

  private fun SoundItem.toViewState(): SoundItemViewState = SoundItemViewState(
    id = id.toViewState(),
    soundUrl = sound.url,
    text = text,
  )
}

fun LessonItemId.toViewState(): LessonItemIdViewState = LessonItemIdViewState(value)
fun LessonItemIdViewState.toDomain(): LessonItemId = LessonItemId(value)
