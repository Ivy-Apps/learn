package ui.screen.intro.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.LocalLottieAnimation
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
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
      IntroAnimation()
      Headline(text = "Become a better software engineer")
      Spacer(modifier = Modifier.height(12.dp))
      Title(
        text = "Learn data structures, algorithms, architecture and software design from first principles."
      )
      Spacer(modifier = Modifier.height(24.dp))
      GoogleSignInButton(
        onClick = { onEvent(IntroViewEvent.OnContinueWithGoogleClick) }
      )
      Spacer(modifier = Modifier.height(12.dp))
      LearnMoreButton(
        onClick = { onEvent(IntroViewEvent.OnLearnMoreClick) }
      )
      Spacer(modifier = Modifier.weight(1f))
      IntroLegalText()
    }
  }
}

@Composable
private fun IntroAnimation(
  modifier: Modifier = Modifier,
) {
  LocalLottieAnimation(
    modifier = modifier.size(400.dp),
    animationFile = "intro_lottie_anim.json",
    repeat = true
  )
}

@Composable
private fun LearnMoreButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  IvyButton(
    modifier = modifier,
    appearance = ButtonAppearance.Text(style = ButtonStyle.Secondary),
    text = {
      Text("Learn more")
    },
    onClick = onClick,
  )
}

