package ui.screen.lesson.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.ScreenType.*
import component.screenType
import kotlinx.coroutines.delay
import ui.screen.lesson.LessonItemViewState
import ui.screen.lesson.LessonViewState
import ui.screen.lesson.OpenQuestionItemViewState
import ui.screen.lesson.QuestionItemViewState

@Composable
fun AutoScrollEffect(
    listState: LazyListState,
    viewState: LessonViewState,
) {
    val items = viewState.items
    val itemsCount = items.size
    LaunchedEffect(itemsCount, viewState.cta) {
        if (itemsCount >= 2) {
            // ensure auto scrolls works for images that are loading
            repeat(4) {
                listState.animateScrollToItem(items.lastIndex)
                delay(200)
            }
        }
    }
}

fun LazyListScope.autoScrollEmptySpace() {
    item("empty_space") {
        Spacer(
            Modifier.height(
                when (screenType()) {
                    Mobile, Tablet -> 32.dp
                    Desktop -> 136.dp
                }
            )
        )
    }
}

private fun LessonItemViewState.isQuestion(): Boolean = when (this) {
    is QuestionItemViewState, is OpenQuestionItemViewState -> true
    else -> false
}