package component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import ui.theme.IvyTheme

@Composable
fun SubTitle(
  text: String,
  modifier: Modifier = Modifier,
  fontWeight: FontWeight? = null,
  maxLines: Int = Int.MAX_VALUE,
  overflow: TextOverflow = TextOverflow.Clip,
) {
  Text(
    modifier = modifier,
    text = text,
    style = IvyTheme.typography.b1,
    fontWeight = fontWeight,
    maxLines = maxLines,
    overflow = overflow,
  )
}