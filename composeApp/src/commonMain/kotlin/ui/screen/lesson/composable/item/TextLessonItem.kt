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
import ui.screen.lesson.composable.ItemSpacingMedium

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

        TextStyleViewState.Body -> BodyText(
            modifier = modifier.padding(top = ItemSpacing),
            text = viewState.text,
        )

        TextStyleViewState.BodyMediumSpacing -> BodyText(
            modifier = modifier.padding(top = ItemSpacingMedium),
            text = viewState.text,
        )

        TextStyleViewState.BodyBigSpacing -> BodyText(
            modifier = modifier.padding(top = ItemSpacingBig),
            text = viewState.text,
        )
    }
}

@Composable
private fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
) {
    BodyBig(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Start,
    )
}