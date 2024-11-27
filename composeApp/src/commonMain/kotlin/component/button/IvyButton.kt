package component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed interface ButtonStyle {
    data class FilledButton(val state: ButtonState) : ButtonStyle
    data class OutlinedButton(val state: ButtonState) : ButtonStyle
}

enum class ButtonState {
    PrimaryVariant,
    SecondaryVariant,
    Success,
    Error
}

@Composable
fun IvyButton(
    style: ButtonStyle,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null,
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