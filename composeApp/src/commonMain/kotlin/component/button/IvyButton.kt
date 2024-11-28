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
    enabled: Boolean = true,
    text: @Composable (RowScope.() -> Unit)? = null,
    icon: @Composable (RowScope.() -> Unit)? = null,
    iconRight: @Composable (RowScope.() -> Unit)? = null,
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

            text != null -> {
                text()
            }
        }
    }
}

@Composable
private fun ButtonWrapper(
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

@Composable
private fun ButtonAppearance.buttonColors(): ButtonColors {
    return when (this) {
        is ButtonAppearance.Filled -> this.colors()
        is ButtonAppearance.Outlined -> this.colors()
        is ButtonAppearance.Text -> this.colors()
    }
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
            contentColor = MaterialTheme.colorsExt.backgroundVariant
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
            contentColor = MaterialTheme.colorsExt.backgroundVariant
        )

        ButtonStyle.Destructive -> ButtonDefaults.textButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.error
        )
    }
}

@Composable
private fun ButtonStyle.borderColor(): Color {
    return when (this) {
        ButtonStyle.Primary -> MaterialTheme.colors.primary
        ButtonStyle.Secondary -> MaterialTheme.colors.secondary
        ButtonStyle.Neutral -> MaterialTheme.colorsExt.backgroundVariant
        ButtonStyle.Destructive -> MaterialTheme.colors.error
    }
}