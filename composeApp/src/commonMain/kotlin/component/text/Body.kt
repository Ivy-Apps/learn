package component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ui.theme.IvyTheme

@Composable
fun BodyBig(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IvyTheme.typography.b1.copy(
            fontSize = 18.sp,
            lineHeight = 24.sp,
        ),
        textAlign = textAlign,
    )
}


@Composable
fun Body(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = IvyTheme.typography.b1
    )
}

@Composable
fun BodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
    fontWeight: FontWeight? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = IvyTheme.typography.b2,
        fontWeight = fontWeight,
    )
}