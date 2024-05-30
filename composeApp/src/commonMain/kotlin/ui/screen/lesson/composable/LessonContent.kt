package ui.screen.lesson.composable

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
        val horizontalPadding = platformHorizontalPadding()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                bottom = 48.dp,
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

                    is ImageItemViewState -> {
                        // TODO
                    }

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
                        onAnswerCheckChange = { answerViewState, checked ->
                            onEvent(
                                LessonViewEvent.OnAnswerCheckChange(
                                    questionId = it.id,
                                    answerId = answerViewState.id,
                                    checked = checked
                                )
                            )
                        },
                        onCheckClick = {
                            onEvent(LessonViewEvent.OnCheckQuestionClick(it.id))
                        }
                    )

                    is TextItemViewState -> TextLessonItem(it)
                }
            }
        }
    }
}