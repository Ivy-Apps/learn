package ui.screen.lesson.composable.item.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ui.theme.IvyTheme

@Composable
fun QuestionText(
  text: String,
  modifier: Modifier = Modifier
) {
  Text(
    modifier = modifier,
    text = text,
    style = IvyTheme.typography.b1,
    fontWeight = FontWeight.SemiBold,
  )
}