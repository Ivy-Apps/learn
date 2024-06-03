package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import component.text.BodyBig
import component.text.HeadlineSmall
import ui.screen.lesson.TextItemViewState
import ui.screen.lesson.TextStyleViewState
import ui.screen.lesson.composable.ItemSpacing
import ui.screen.lesson.composable.ItemSpacingBig

@Composable
fun TextLessonItem(
    viewState: TextItemViewState,
    modifier: Modifier = Modifier
) {
    when (viewState.style) {
        TextStyleViewState.Heading -> HeadlineSmall(
            modifier = modifier.padding(top = ItemSpacingBig),
            text = viewState.text
        )

        TextStyleViewState.Body -> BodyBig(
            modifier = modifier.padding(top = ItemSpacing),
            text = viewState.text,
            textAlign = TextAlign.Center,
        )
    }
}