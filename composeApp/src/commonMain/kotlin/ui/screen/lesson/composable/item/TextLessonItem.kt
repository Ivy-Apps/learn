package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.ScreenType.*
import component.screenType
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
        TextStyleViewState.Heading -> LessonHeadlineText(
            modifier = modifier.padding(top = ItemSpacingBig),
            text = viewState.text
        )

        TextStyleViewState.Body -> LessonBodyText(
            modifier = modifier.padding(top = ItemSpacing),
            text = viewState.text,
        )

        TextStyleViewState.BodyMediumSpacing -> LessonBodyText(
            modifier = modifier.padding(top = ItemSpacingMedium),
            text = viewState.text,
        )

        TextStyleViewState.BodyBigSpacing -> LessonBodyText(
            modifier = modifier.padding(top = ItemSpacingBig),
            text = viewState.text,
        )
    }
}

@Composable
private fun LessonHeadlineText(
    text: String,
    modifier: Modifier = Modifier,
) {
    HeadlineSmall(
        modifier = modifier
            .then(
                when (screenType()) {
                    Desktop, Tablet -> Modifier.sizeIn(
                        maxWidth = 500.dp
                    )

                    Mobile -> Modifier
                }
            ),
        text = text
    )
}

@Composable
private fun LessonBodyText(
    text: String,
    modifier: Modifier = Modifier,
) {
    BodyBig(
        modifier = modifier.then(
            when (screenType()) {
                Desktop, Tablet -> Modifier.sizeIn(
                    minWidth = 500.dp,
                    maxWidth = 500.dp
                )

                Mobile -> Modifier.sizeIn(
                    minWidth = 500.dp,
                )
            }
        ),
        text = text,
        textAlign = TextAlign.Start,
    )
}