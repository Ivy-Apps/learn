package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.button.ButtonAppearance
import component.button.IvyButton
import ui.screen.lesson.SoundItemViewState
import ui.screen.lesson.composable.ItemSpacing

@Composable
fun SoundLessonItem(
  viewState: SoundItemViewState,
  onClick: (soundUrl: String) -> Unit,
  modifier: Modifier = Modifier,
) {
  IvyButton(
    modifier = modifier.padding(top = ItemSpacing),
    appearance = ButtonAppearance.Outlined.Secondary,
    text = {
      Text(text = viewState.text)
    },
    icon = {
      Icon(
        imageVector = Icons.Default.PlayArrow,
        contentDescription = null
      )
    },
    onClick = { onClick(viewState.soundUrl) }
  )
}