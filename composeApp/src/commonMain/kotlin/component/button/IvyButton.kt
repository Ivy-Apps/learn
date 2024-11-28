package component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.colorsExt

sealed interface ButtonAppearance {
    val style: ButtonStyle

    data class Filled(override val style: ButtonStyle) : ButtonAppearance
    data class Outlined(override val style: ButtonStyle) : ButtonAppearance
    data class Text(override val style: ButtonStyle) : ButtonAppearance
}

enum class ButtonStyle {
    Primary,
    Secondary,
    Neutral
}

@Composable
fun IvyButton(
    appearance: ButtonAppearance,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    ButtonWrapper(
        appearance = appearance,
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        when {
            icon != null && text != null -> {
                icon()
                Spacer(Modifier.width(8.dp))
                text()
            }

            text != null && iconRight != null -> {
                text()
                Spacer(Modifier.width(8.dp))
                iconRight()
            }

            icon != null -> {
                icon()
            }
        }
    }
}

@Composable
fun ButtonWrapper(
    appearance: ButtonAppearance,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
) {
    val contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    val colors = appearance.buttonColors()

    when (appearance) {
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
                    color = appearance.style.borderColor()
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

fun ButtonAppearance.buttonColors(): ButtonColors {
    return when (this) {
        is ButtonAppearance.Filled -> {
            when (this.style) {
                ButtonStyle.Primary -> ButtonDefaults.buttonColors()
                ButtonStyle.Secondary -> ButtonDefaults.buttonColors()
                ButtonStyle.Neutral -> ButtonDefaults.buttonColors()
            }
        }

        is ButtonAppearance.Outlined -> {
            when (this.style) {
                ButtonStyle.Primary -> ButtonDefaults.outlinedButtonColors()
                ButtonStyle.Secondary -> ButtonDefaults.outlinedButtonColors()
                ButtonStyle.Neutral -> ButtonDefaults.outlinedButtonColors()
            }
        }

        is ButtonAppearance.Text -> {
            when (this.style) {
                ButtonStyle.Primary -> ButtonDefaults.textButtonColors()
                ButtonStyle.Secondary -> ButtonDefaults.textButtonColors()
                ButtonStyle.Neutral -> ButtonDefaults.textButtonColors()
            }
        }
    }
}

fun ButtonStyle.borderColor(): Color {
    return when (this) {
        ButtonStyle.Primary -> MaterialTheme.colors.primary
        ButtonStyle.Secondary -> MaterialTheme.colors.secondary
        ButtonStyle.Neutral -> MaterialTheme.colorsExt.backgroundVariant
    }
}