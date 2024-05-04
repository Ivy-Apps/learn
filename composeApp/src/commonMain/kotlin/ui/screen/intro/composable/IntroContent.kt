package ui.screen.intro.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
            modifier = Modifier.fillMaxSize()
                .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            LearnLottieAnimation(
                modifier = Modifier.size(400.dp),
                animationFile = "intro_lottie_anim.json",
                repeat = true
            )
            Headline(text = "Hello!")
            Spacer(modifier = Modifier.height(12.dp))
            Title(text = "Learn programming by thinking.")
            Spacer(modifier = Modifier.height(24.dp))
            ContinueButton(onClick = { onEvent(IntroViewEvent.OnContinueClick) })
            Spacer(modifier = Modifier.weight(1f))
            IntroLegalText()
        }
    }
}

@Composable
private fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        onClick = onClick,
    ) {
        Text("Let's go")
    }
}