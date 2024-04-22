package ui.screen.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ivy.di.Di
import ui.navigation.Navigation
import ui.navigation.Screen
import ui.theme.*

class ColorDemoScreen : Screen() {
    private val navigation: Navigation = Di.get()

    override fun onDi(): Di.ScreenScope.() -> Unit = {}

    @Composable
    override fun Content() {
        ColorsDemo()
    }

    @Composable
    private fun ColorsDemo() {
        LazyColumn {
            item {
                Button(
                    onClick = {
                        navigation.back()
                    }
                ) {
                    Text("Back")
                }
            }
            colorItem(name = "Blue", color = Blue)
            colorItem(name = "Blue Variant", color = BlueVariant)
            colorItem(name = "Orange", color = Orange)
            colorItem(name = "Orange Variant", color = OrangeVariant)
            colorItem(name = "Red", color = Red)
            colorItem(name = "Green", color = Green)
            colorItem(name = "Gray", color = Gray)
        }
    }

    private fun LazyListScope.colorItem(name: String, color: Color) {
        item {
            ColorItem(name = name, color = color)
        }
    }

    @Composable
    private fun ColorItem(name: String, color: Color) {
        Text(name)
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(96.dp)
                    .background(Color.White)
                    .padding(24.dp)
                    .background(color)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(96.dp)
                    .background(Color.Black)
                    .padding(24.dp)
                    .background(color)
            )
        }
    }
}