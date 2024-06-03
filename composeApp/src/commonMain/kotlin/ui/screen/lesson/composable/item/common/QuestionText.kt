package ui.screen.lesson.composable.item.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import component.text.SubTitle

@Composable
fun QuestionText(
    text: String,
    modifier: Modifier = Modifier
) {
    SubTitle(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold
    )
}