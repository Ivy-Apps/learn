package ui.screen.lesson.composable.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.ScreenType.Mobile
import component.screenType
import ui.screen.lesson.CodeItemViewState
import ui.screen.lesson.composable.ItemSpacingMedium
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
      text = viewState.code,
      style = MaterialTheme.typography.body1.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp,
      ),
      textAlign = TextAlign.Start,
    )
  }
}
