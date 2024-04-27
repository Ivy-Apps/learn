package ui.screen.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ivy.di.Di
import ui.navigation.Navigation
import ui.navigation.Screen

class IntroScreen : Screen() {
    private val navigation: Navigation = Di.get()

    override fun onDi(): Di.ScreenScope.() -> Unit = {}

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // TODO
        }
    }
}