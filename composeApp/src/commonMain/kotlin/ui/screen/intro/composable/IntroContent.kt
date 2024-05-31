package ui.screen.intro.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.LearnLottieAnimation
import component.button.CtaButton
import component.platformHorizontalPadding
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
                .padding(
                    vertical = 24.dp,
                    horizontal = platformHorizontalPadding()
                ),
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
    CtaButton(
        modifier = modifier,
        text = "LET'S GO!",
        onClick = onClick
    )
}