package component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colors.onBackground
) {
    Text(
        modifier = modifier,
        fontWeight = fontWeight,
        text = text,
        style = MaterialTheme.typography.h6,
        color = color
    )
}