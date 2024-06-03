package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.button.PrimaryOutlinedButton
import component.text.SubTitle
import kotlinx.collections.immutable.ImmutableList
import ui.screen.lesson.ChoiceItemViewState
import ui.screen.lesson.ChoiceOptionViewState
import ui.screen.lesson.composable.ItemSpacingBig

@Composable
fun ChoiceLessonItem(
    viewState: ChoiceItemViewState,
    onChoiceClick: (ChoiceOptionViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(top = ItemSpacingBig),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            QuestionText(text = viewState.question)
            Spacer(Modifier.height(8.dp))
            ChoicesOptions(
                options = viewState.options,
                onChoiceClick = onChoiceClick,
            )
        }
    }
}

@Composable
private fun QuestionText(
    text: String,
    modifier: Modifier = Modifier
) {
    SubTitle(modifier = modifier, text = text)
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
    PrimaryOutlinedButton(
        modifier = modifier,
        text = viewState.text,
        onClick = { onClick(viewState) },
    )
}