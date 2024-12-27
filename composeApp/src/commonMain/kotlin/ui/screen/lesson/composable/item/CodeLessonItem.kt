package ui.screen.lesson.composable.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.PythonSyntaxHighlight
import component.ScreenType.Mobile
import component.highlightSyntax
import component.screenType
import ui.screen.lesson.CodeItemViewState
import ui.screen.lesson.composable.ItemSpacingMedium
import ui.theme.IvyTheme
import ui.theme.colorsExt

@Composable
fun CodeLessonItem(
  viewState: CodeItemViewState,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.padding(top = ItemSpacingMedium),
    shape = RoundedCornerShape(12.dp),
    backgroundColor = MaterialTheme.colorsExt.backgroundVariant,
    contentColor = MaterialTheme.colorsExt.onBackgroundVariant,
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
  ) {
    Text(
      modifier = modifier
        .padding(
          vertical = 24.dp,
          horizontal = when (screenType()) {
            Mobile -> 8.dp
            else -> 24.dp
          }
        )
        .then(
          when (screenType()) {
            Mobile -> Modifier.sizeIn(
              minWidth = 500.dp,
            )

            else -> Modifier
          }
        ),
      text = highlightSyntax(
        code = viewState.code,
        syntaxHighlight = remember { PythonSyntaxHighlight() }
      ),
      style = IvyTheme.typography.b2,
      textAlign = TextAlign.Start,
    )
  }
}