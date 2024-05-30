package ui.screen.lesson.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.platformHorizontalPadding
import ui.screen.lesson.*
import ui.screen.lesson.composable.item.ImageLessonItem
import ui.screen.lesson.composable.item.QuestionLessonItem
import ui.screen.lesson.composable.item.TextLessonItem

@Composable
fun LessonContent(
    viewState: LessonViewState,
    onEvent: (LessonViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(onBackClick = {
            onEvent(LessonViewEvent.OnBackClick)
        }),
        title = viewState.title
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
                Cta(
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .padding(bottom = 48.dp),
                    viewState = viewState.cta,
                    onContinueClick = { itemId ->
                        onEvent(LessonViewEvent.OnContinueClick(itemId))
                    },
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
    val horizontalPadding = platformHorizontalPadding()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
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
        ) {
            when (it) {
                is ChoiceItemViewState -> {
                    // TODO
                }

                is ImageItemViewState -> ImageLessonItem(it)

                is LessonNavigationItemViewState -> {
                    // TODO
                }

                is LinkItemViewState -> {
                    // TODO
                }

                is LottieAnimationItemViewState -> {
                    // TODO
                }

                is MysteryItemViewState -> {
                    // TODO
                }

                is OpenQuestionItemViewState -> {
                    // TODO
                }

                is QuestionItemViewState -> QuestionLessonItem(
                    viewState = it,
                    onAnswerCheckChange = { type, answerViewState, checked ->
                        onEvent(
                            QuestionViewEvent.AnswerCheckChange(
                                questionId = it.id,
                                questionType = type,
                                answerId = answerViewState.id,
                                checked = checked,
                            )
                        )
                    },
                    onCheckClick = {
                        onEvent(QuestionViewEvent.CheckClick(it.id))
                    }
                )

                is TextItemViewState -> TextLessonItem(it)
            }
        }
    }
}