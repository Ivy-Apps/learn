package ui.screen.lesson.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.ScreenType.*
import component.screenType
import ui.screen.lesson.*
import ui.screen.lesson.composable.item.*

val ItemSpacing = 12.dp
val ItemSpacingMedium = 24.dp
val ItemSpacingBig = 48.dp

@Composable
fun LessonContent(
    viewState: LessonViewState,
    onEvent: (LessonViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(
            onBackClick = {
                onEvent(LessonViewEvent.OnBackClick)
            }
        ),
        title = viewState.title,
        topBarCenterContent = {
            LessonProgressBar(viewState = viewState.progress)
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
        ) {
            LessonItemsLazyColum(
                viewState = viewState,
                onEvent = onEvent,
            )
            if (viewState.cta != null) {
                CtaBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    viewState = viewState.cta,
                    onContinueClick = { itemId ->
                        onEvent(LessonViewEvent.OnContinueClick(itemId))
                    },
                    onFinishClick = { itemId ->
                        onEvent(LessonViewEvent.OnFinishClick(itemId))
                    }
                )
            }
        }
    }
}

@Composable
private fun LessonItemsLazyColum(
    viewState: LessonViewState,
    onEvent: (LessonViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val horizontalPadding = when (screenType()) {
        Mobile -> 16.dp
        Tablet -> 24.dp
        Desktop -> 64.dp
    }
    val listState = rememberLazyListState()

    AutoScrollEffect(
        listState = listState,
        items = viewState.items,
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            bottom = 96.dp,
            start = horizontalPadding,
            end = horizontalPadding,
        )
    ) {
        items(
            items = viewState.items,
            key = {
                it.id.value
            }
        ) { itemViewState ->
            when (itemViewState) {
                is ChoiceItemViewState -> ChoiceLessonItem(
                    viewState = itemViewState,
                    onChoiceClick = { choiceOptionViewState ->
                        onEvent(
                            LessonViewEvent.OnChoiceClick(
                                questionId = itemViewState.id,
                                choiceId = choiceOptionViewState.id
                            )
                        )
                    }
                )

                is ImageItemViewState -> ImageLessonItem(itemViewState)

                is LessonNavigationItemViewState -> {
                    // TODO
                }

                is LinkItemViewState -> {
                    // TODO
                }

                is LottieAnimationItemViewState -> LottieAnimationLessonItem(itemViewState)

                is MysteryItemViewState -> {
                    // TODO
                }

                is OpenQuestionItemViewState -> {
                    // TODO
                }

                is QuestionItemViewState -> QuestionLessonItem(
                    viewState = itemViewState,
                    onAnswerCheckChange = { type, answerViewState, checked ->
                        onEvent(
                            QuestionViewEvent.OnAnswerCheckChange(
                                questionId = itemViewState.id,
                                questionType = type,
                                answerId = answerViewState.id,
                                checked = checked,
                            )
                        )
                    },
                    onCheckClick = { answers ->
                        onEvent(QuestionViewEvent.OnCheckClick(itemViewState.id, answers))
                    }
                )

                is TextItemViewState -> TextLessonItem(itemViewState)

                is SoundItemViewState -> SoundLessonItem(
                    viewState = itemViewState,
                    onClick = { soundUrl ->
                        onEvent(LessonViewEvent.OnSoundClick(soundUrl))
                    }
                )
            }
        }
        autoScrollEmptySpace()
    }
}