package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import component.ScreenType
import component.screenType

@Immutable
object IvyTheme {
  val typography: Typography
    @ReadOnlyComposable
    @Composable
    get() = LocalTypography.current
}

private val LightColorScheme = lightColors(
  primary = Blue,
  primaryVariant = BlueVariant,
  secondary = Orange,
  secondaryVariant = OrangeVariant,
  background = Color.White,
  surface = Color.White,
  error = Red,
  onPrimary = Color.White,
  onSecondary = Color.White,
  onBackground = Color.Black,
  onSurface = Color.Black,
  onError = Color.White
)

private val DarkColorScheme = darkColors(
  primary = Blue,
  primaryVariant = BlueVariant,
  secondary = Orange,
  secondaryVariant = OrangeVariant,
  background = Color.Black,
  surface = Color.Black,
  error = Red,
  onPrimary = Color.White,
  onSecondary = Color.White,
  onBackground = Color.White,
  onSurface = Color.White,
  onError = Color.White
)

val MaterialTheme.colorsExt: ExtendedColors
  @Composable
  @ReadOnlyComposable
  get() = if (colors.isLight) {
    ExtendedColors(
      success = Green,
      onSuccess = Color.White,
      backgroundVariant = Light,
      onBackgroundVariant = Color.Black
    )
  } else {
    ExtendedColors(
      success = Green,
      onSuccess = Color.White,
      backgroundVariant = Dark,
      onBackgroundVariant = Color.White
    )
  }

data class ExtendedColors(
  val backgroundVariant: Color,
  val onBackgroundVariant: Color,
  val success: Color,
  val onSuccess: Color
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

  val shape = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(percent = 50),
    large = RoundedCornerShape(percent = 50)
  )

  CompositionLocalProvider(
    LocalTypography provides when (screenType()) {
      ScreenType.Mobile -> MobileTypography
      ScreenType.Tablet -> MobileTypography
      ScreenType.Desktop -> DesktopTypography
    }
  ) {
    MaterialTheme(
      colors = colorScheme,
      shapes = shape,
      content = content
    )
  }
}