package component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Headline(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IvyTheme.typography.h4,
        color = color,
        fontWeight = FontWeight.Black,
    )
}

@Composable
fun HeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IvyTheme.typography.h5,
        color = color,
        fontWeight = FontWeight.Black,
    )
}