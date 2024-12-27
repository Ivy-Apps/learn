package component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.theme.IvyTheme
import ui.theme.colorsExt

@Composable
fun IvySwitch(
  checked: Boolean,
  modifier: Modifier = Modifier,
  onCheckedChange: (Boolean) -> Unit,
  text: @Composable () -> Unit,
) {
  Row(
    modifier = modifier
      .clip(MaterialTheme.shapes.medium)
      .clickable(
        onClick = {
          onCheckedChange(!checked)
        }
      )
      .background(
        color = MaterialTheme.colorsExt.backgroundVariant,
        shape = MaterialTheme.shapes.medium
      )
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    CompositionLocalProvider(
      LocalTextStyle provides IvyTheme.typography.b2.copy(
        color = MaterialTheme.colorsExt.onBackgroundVariant
      )
    ) {
      text()
    }
    Spacer(Modifier.weight(1f))
    Switch(
      checked = checked,
      onCheckedChange = onCheckedChange
    )
  }
}