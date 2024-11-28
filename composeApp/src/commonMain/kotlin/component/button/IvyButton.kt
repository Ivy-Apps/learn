package component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed interface ButtonAppearance {
    val style: ButtonStyle

    data class Filled(override val style: ButtonStyle): ButtonAppearance
    data class Outlined(override val style: ButtonStyle): ButtonAppearance
    data class Text(override val style: ButtonStyle): ButtonAppearance
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
    Button(
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        onClick = onClick
    ) {
        // TODO
    }
}