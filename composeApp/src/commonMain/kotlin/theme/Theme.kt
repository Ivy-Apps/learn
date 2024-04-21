package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColors(
    primary = Blue.light,
    primaryVariant = BlueVariant.light,
    secondary = Orange.light,
    secondaryVariant = OrangeVariant.light,
    background = Color.White,
    surface = Color.White,
    error = Red.light,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val DarkColorScheme = darkColors(
    primary = Blue.dark,
    primaryVariant = BlueVariant.dark,
    secondary = Orange.dark,
    secondaryVariant = OrangeVariant.dark,
    background = Color.Black,
    surface = Color.Black,
    error = Red.dark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)

@Composable
fun LearnTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (darkTheme) {
        true -> DarkColorScheme
        false -> LightColorScheme
    }

    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}