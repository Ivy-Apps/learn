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
import ui.screen.lesson.ChoiceItemViewState
import ui.screen.lesson.ImageItemViewState
import ui.screen.lesson.LessonNavigationItemViewState
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewState
import ui.screen.lesson.LinkItemViewState
import ui.screen.lesson.LottieAnimationItemViewState
import ui.screen.lesson.MysteryItemViewState
import ui.screen.lesson.OpenQuestionItemViewState
import ui.screen.lesson.QuestionItemViewState
import ui.screen.lesson.TextItemViewState
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
                    is ChoiceItemViewState -> TODO()
                    is ImageItemViewState -> TODO()
                    is LessonNavigationItemViewState -> TODO()
                    is LinkItemViewState -> TODO()
                    is LottieAnimationItemViewState -> TODO()
                    is MysteryItemViewState -> TODO()
                    is OpenQuestionItemViewState -> TODO()
                    is QuestionItemViewState -> TODO()
                    is TextItemViewState -> TextLessonItem(it)
                }
            }
        }
    }
}