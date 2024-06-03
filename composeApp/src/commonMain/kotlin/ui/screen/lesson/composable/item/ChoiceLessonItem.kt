package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.text.SubTitle
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
