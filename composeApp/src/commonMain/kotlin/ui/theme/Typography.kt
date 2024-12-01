package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import learn.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

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