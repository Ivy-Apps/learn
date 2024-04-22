package ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ivy.di.Di
import ui.navigation.Navigation
import ui.navigation.Screen
import ui.screen.debug.ColorDemoScreen

class HomeScreen : Screen() {

    private val navigation: Navigation = Di.get()

    override fun onDi(): Di.ScreenScope.() -> Unit = {}

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                navigation.navigate(ColorDemoScreen())
            }) {
                Text("Colors demo")
            }
        }
    }
}