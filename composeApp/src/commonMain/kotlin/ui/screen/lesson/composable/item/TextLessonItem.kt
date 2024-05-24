package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.text.Body
import component.text.Headline
import ui.screen.lesson.TextItemViewState
import ui.screen.lesson.TextStyleViewState

@Composable
fun TextLessonItem(
    viewState: TextItemViewState,
    modifier: Modifier = Modifier
) {
    when (viewState.style) {
        TextStyleViewState.Heading -> Headline(
            modifier = modifier.padding(top = 16.dp),
            text = viewState.text
        )

        TextStyleViewState.Body -> Body(
            modifier = modifier.padding(top = 8.dp),
            text = viewState.text
        )
    }
}