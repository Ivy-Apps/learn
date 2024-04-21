package theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ColorShades(
    val light: Color,
    val dark: Color
)

val Blue = ColorShades(
    light = Color(0xFF008AE0),
    dark = Color(0xFF008AE0)
)

val Orange = ColorShades(
    light = Color(0xFFF59331),
    dark = Color(0xFFF59331)
)

val Red = ColorShades(
    light = Color(0xFFF53D3D),
    dark = Color(0xFFF53D3D)
)

val Green = ColorShades(
    light = Color(0xFF12B880),
    dark = Color(0xFF12B880)
)

val Gray = ColorShades(
    light = Color(0xFFCBCBD6),
    dark = Color(0xFFCBCBD6)
)

@Composable
fun ColorsDemo() {
    LazyColumn {
        colorItem(name = "Blue", colorShades = Blue)
        colorItem(name = "Orange", colorShades = Orange)
        colorItem(name = "Red", colorShades = Red)
        colorItem(name = "Green", colorShades = Green)
        colorItem(name = "Gray", colorShades = Gray)
    }
}

fun LazyListScope.colorItem(name: String, colorShades: ColorShades) {
    item {
        ColorItem(name = name, colorShades = colorShades)
    }
}

@Composable
private fun ColorItem(name: String, colorShades: ColorShades) {
    Text(name)
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .background(Color.White)
                .padding(24.dp)
                .background(colorShades.light)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .background(Color.Black)
                .padding(24.dp)
                .background(colorShades.dark)
        )
    }
}