package ui.screen.home.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ui.screen.home.HomeItemViewState
import ui.theme.Gray
import ui.theme.IvyTheme

@Composable
fun HomeTopicDivider(
  section: HomeItemViewState.Section,
  modifier: Modifier = Modifier
) {
  Text(
    modifier = modifier,
    text = section.title,
    color = Gray,
    style = IvyTheme.typography.b2,
    fontWeight = FontWeight.SemiBold
  )
}