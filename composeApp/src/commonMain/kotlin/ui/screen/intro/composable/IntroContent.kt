package ui.screen.intro.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.screen.intro.IntroViewEvent
import ui.screen.intro.IntroViewState

@Composable
fun IntroContent(
    viewState: IntroViewState,
    onEvent: (IntroViewEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello!")
        Text(text = "Learn programming by thinking.")
        Button(onClick = {
            onEvent(IntroViewEvent.OnContinueClick)
        }) {
            Text("Let's go")
        }
    }
}