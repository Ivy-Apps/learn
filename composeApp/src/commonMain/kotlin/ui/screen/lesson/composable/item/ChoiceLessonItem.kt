package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.button.PrimaryButton
import kotlinx.collections.immutable.ImmutableList
import ui.screen.lesson.ChoiceItemViewState
import ui.screen.lesson.ChoiceOptionViewState
import ui.screen.lesson.composable.ItemSpacingBig
import ui.screen.lesson.composable.item.common.QuestionCard
import ui.screen.lesson.composable.item.common.QuestionText

@Composable
fun ChoiceLessonItem(
    viewState: ChoiceItemViewState,
    onChoiceClick: (ChoiceOptionViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    QuestionCard(
        modifier = modifier.padding(top = ItemSpacingBig),
    ) {
        QuestionText(text = viewState.question)
        Spacer(Modifier.height(16.dp))
        ChoicesOptions(
            options = viewState.options,
            onChoiceClick = onChoiceClick,
        )
    }
}

@Composable
private fun ChoicesOptions(
    options: ImmutableList<ChoiceOptionViewState>,
    onChoiceClick: (ChoiceOptionViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for ((index, itemViewState) in options.withIndex()) {
            ChoiceOption(
                viewState = itemViewState,
                onClick = { onChoiceClick(it) },
            )
            if (index != options.lastIndex) {
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun ChoiceOption(
    viewState: ChoiceOptionViewState,
    onClick: (ChoiceOptionViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryButton(
        modifier = modifier,
        text = viewState.text,
        onClick = { onClick(viewState) },
    )
}