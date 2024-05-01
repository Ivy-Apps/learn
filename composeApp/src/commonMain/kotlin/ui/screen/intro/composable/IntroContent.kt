package ui.screen.intro.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.LearnLottieAnimation
import component.text.Headline
import component.text.Title
import ui.screen.intro.IntroViewEvent
import ui.screen.intro.IntroViewState

@Composable
fun IntroContent(
    viewState: IntroViewState,
    onEvent: (IntroViewEvent) -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LearnLottieAnimation(
                modifier = Modifier.size(400.dp),
                animationFile = "intro_lottie_anim.json",
                repeat = true
            )
            Headline(text = "Hello!")
            Spacer(modifier = Modifier.height(4.dp))
            Title(text = "Learn programming by thinking.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                onEvent(IntroViewEvent.OnContinueClick)
            }) {
                Text("Let's go")
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "By continuing you agree with Terms & conditions and " +
                        "Privacy Policy",
                style = MaterialTheme.typography.body2
            )
        }
    }
}