package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import learn.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

val LocalTypography = compositionLocalOf<Typography> { error("No typography composition local") }

@Immutable
interface Typography {
  @get:Composable
  val h1: TextStyle

  @get:Composable
  val h2: TextStyle

  @get:Composable
  val b1: TextStyle

  @get:Composable
  val b2: TextStyle

  @get:Composable
  val caption: TextStyle
}

@Immutable
object MobileTypography : Typography {
  override val h1
    @Composable
    get() = ivyTextStyle(
      fontSize = 28.sp,
      lineHeight = 30.sp
    )

  override val h2
    @Composable
    get() = ivyTextStyle(
      fontSize = 20.sp,
      lineHeight = 22.sp
    )

  override val b1
    @Composable
    get() = ivyTextStyle(
      fontSize = 16.sp,
      lineHeight = 18.sp
    )

  override val b2
    @Composable
    get() = ivyTextStyle(
      fontSize = 14.sp,
      lineHeight = 16.sp
    )

  override val caption
    @Composable
    get() = ivyTextStyle(
      fontSize = 12.sp,
      lineHeight = 14.sp
    )
}

@Immutable
object DesktopTypography : Typography {
  override val h1
    @Composable
    get() = ivyTextStyle(
      fontSize = 32.sp,
      lineHeight = 34.sp
    )

  override val h2
    @Composable
    get() = ivyTextStyle(
      fontSize = 24.sp,
      lineHeight = 26.sp
    )

  override val b1
    @Composable
    get() = ivyTextStyle(
      fontSize = 20.sp,
      lineHeight = 22.sp
    )

  override val b2
    @Composable
    get() = ivyTextStyle(
      fontSize = 16.sp,
      lineHeight = 18.sp
    )

  override val caption
    @Composable
    get() = ivyTextStyle(
      fontSize = 14.sp,
      lineHeight = 16.sp
    )
}

@Composable
private fun ivyTextStyle(
  fontSize: TextUnit,
  lineHeight: TextUnit
): TextStyle {
  return TextStyle(
    fontSize = fontSize,
    lineHeight = lineHeight,
    fontFamily = RobotoFontFamily,
  )
}

val RobotoFontFamily
  @Composable
  get() = FontFamily(
    Font(Res.font.roboto_regular, FontWeight.Normal),
    Font(Res.font.roboto_bold, FontWeight.Bold),
    Font(Res.font.roboto_italic, FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.roboto_bold_italic, FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.roboto_light, FontWeight.Light),
    Font(Res.font.roboto_light_italic, FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.roboto_medium, FontWeight.Medium),
    Font(Res.font.roboto_medium_italic, FontWeight.Medium, style = FontStyle.Italic),
    Font(Res.font.roboto_black, FontWeight.Black),
    Font(Res.font.roboto_black_italic, FontWeight.Black, style = FontStyle.Italic),
    Font(Res.font.roboto_thin, FontWeight.Thin),
    Font(Res.font.roboto_thin_italic, FontWeight.Thin, style = FontStyle.Italic)
  )