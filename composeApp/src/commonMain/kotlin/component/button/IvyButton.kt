package component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.theme.Gray
import ui.theme.IvyTheme
import ui.theme.colorsExt

sealed interface ButtonAppearance {
  data object Focused : ButtonAppearance

  sealed interface Filled : ButtonAppearance {
    data object Primary : Filled
    data object Secondary : Filled
    data object Neutral : Filled
    data object Destructive : Filled
  }

  sealed interface Outlined : ButtonAppearance {
    data object Primary : Outlined
    data object Secondary : Outlined
    data object Neutral : Outlined
    data object Destructive : Outlined
  }

  sealed interface Text : ButtonAppearance {
    data object Primary : Text
    data object Secondary : Text
    data object Neutral : Text
    data object Destructive : Text
  }
}

sealed interface ButtonStyle {
  data object Primary : ButtonStyle
  data object Secondary : ButtonStyle
  data object Neutral : ButtonStyle
  data object Destructive : ButtonStyle
}

@Composable
fun IvyButton(
  appearance: ButtonAppearance,
  modifier: Modifier = Modifier,
  loading: Boolean = false,
  enabled: Boolean = true,
  text: @Composable (RowScope.() -> Unit),
  icon: @Composable (RowScope.() -> Unit)? = null,
  iconRight: @Composable (RowScope.() -> Unit)? = null,
  onClick: () -> Unit,
) {
  val contentPadding = if (appearance is ButtonAppearance.Focused) {
    PaddingValues(
      horizontal = 16.dp,
      vertical = 12.dp
    )
  } else {
    PaddingValues(
      horizontal = 16.dp,
      vertical = 8.dp
    )
  }

  ButtonWrapper(
    modifier = modifier,
    appearance = appearance,
    contentPadding = contentPadding,
    enabled = enabled && !loading,
    onClick = onClick
  ) {
    CompositionLocalProvider(
      LocalTextStyle provides IvyTheme.typography.b2.copy(
        fontWeight = FontWeight.Medium
      )
    ) {
      when {
        loading -> {
          CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = LocalContentColor.current
          )
        }

        icon != null -> {
          icon()
          Spacer(Modifier.width(8.dp))
          text()
        }

        iconRight != null -> {
          text()
          Spacer(Modifier.width(8.dp))
          iconRight()
        }

        else -> {
          text()
        }
      }
    }
  }
}

@Composable
private fun ButtonWrapper(
  appearance: ButtonAppearance,
  contentPadding: PaddingValues,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  onClick: () -> Unit,
  content: @Composable (RowScope.() -> Unit)
) {
  val colors = appearance.buttonColors()

  when (appearance) {
    ButtonAppearance.Focused -> {
      Button(
        modifier = modifier,
        enabled = enabled,
        elevation = ButtonDefaults.elevation(
          defaultElevation = 6.dp,
          pressedElevation = 12.dp,
          disabledElevation = 0.dp,
          hoveredElevation = 8.dp,
          focusedElevation = 8.dp
        ),
        contentPadding = contentPadding,
        colors = colors,
        onClick = onClick,
        content = content
      )
    }

    is ButtonAppearance.Filled -> {
      Button(
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        colors = colors,
        onClick = onClick,
        content = content
      )
    }

    is ButtonAppearance.Outlined -> {
      OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        colors = colors,
        border = BorderStroke(
          width = 1.dp,
          color = appearance.style.outlinedBorderColor()
        ),
        onClick = onClick,
        content = content
      )
    }

    is ButtonAppearance.Text -> {
      TextButton(
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        colors = colors,
        onClick = onClick,
        content = content
      )
    }
  }
}

@Composable
private fun ButtonAppearance.buttonColors(): ButtonColors {
  return when (this) {
    is ButtonAppearance.Filled -> this.colors()
    is ButtonAppearance.Outlined -> this.colors()
    is ButtonAppearance.Text -> this.colors()
    is ButtonAppearance.Focused -> this.colors()
  }
}

@Composable
private fun ButtonAppearance.Focused.colors(): ButtonColors {
  return ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = MaterialTheme.colors.onPrimary
  )
}

@Composable
private fun ButtonAppearance.Filled.colors(): ButtonColors {
  return when (this.style) {
    ButtonStyle.Primary -> ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.primary,
      contentColor = MaterialTheme.colors.onPrimary
    )

    ButtonStyle.Secondary -> ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.secondary,
      contentColor = MaterialTheme.colors.onSecondary
    )

    ButtonStyle.Neutral -> ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colorsExt.backgroundVariant,
      contentColor = MaterialTheme.colorsExt.onBackgroundVariant
    )

    ButtonStyle.Destructive -> ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.error,
      contentColor = MaterialTheme.colors.onError
    )
  }
}

@Composable
private fun ButtonAppearance.Outlined.colors(): ButtonColors {
  return when (this.style) {
    ButtonStyle.Primary -> ButtonDefaults.outlinedButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.primary
    )

    ButtonStyle.Secondary -> ButtonDefaults.outlinedButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.secondary
    )

    ButtonStyle.Neutral -> ButtonDefaults.outlinedButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = Gray
    )

    ButtonStyle.Destructive -> ButtonDefaults.outlinedButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.error
    )
  }
}

@Composable
private fun ButtonAppearance.Text.colors(): ButtonColors {
  return when (this.style) {
    ButtonStyle.Primary -> ButtonDefaults.textButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.primary
    )

    ButtonStyle.Secondary -> ButtonDefaults.textButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.secondary
    )

    ButtonStyle.Neutral -> ButtonDefaults.textButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = Gray
    )

    ButtonStyle.Destructive -> ButtonDefaults.textButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = MaterialTheme.colors.error
    )
  }
}

@Composable
private fun ButtonStyle.outlinedBorderColor(): Color {
  return when (this) {
    ButtonStyle.Primary -> MaterialTheme.colors.primary
    ButtonStyle.Secondary -> MaterialTheme.colors.secondary
    ButtonStyle.Neutral -> Gray
    ButtonStyle.Destructive -> MaterialTheme.colors.error
  }
}

private val ButtonAppearance.style: ButtonStyle
  get() = when (this) {
    ButtonAppearance.Filled.Destructive,
    ButtonAppearance.Outlined.Destructive,
    ButtonAppearance.Text.Destructive
      -> ButtonStyle.Destructive

    ButtonAppearance.Outlined.Neutral,
    ButtonAppearance.Filled.Neutral,
    ButtonAppearance.Text.Neutral
      -> ButtonStyle.Neutral

    ButtonAppearance.Focused,
    ButtonAppearance.Filled.Primary,
    ButtonAppearance.Outlined.Primary,
    ButtonAppearance.Text.Primary
      -> ButtonStyle.Primary

    ButtonAppearance.Filled.Secondary,
    ButtonAppearance.Outlined.Secondary,
    ButtonAppearance.Text.Secondary
      -> ButtonStyle.Secondary
  }