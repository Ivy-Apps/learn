package ui.screen.intro.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.LocalLottieAnimation
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import component.platformHorizontalPadding
import ui.screen.intro.IntroViewEvent
import ui.screen.intro.IntroViewState
import ui.theme.Gray


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
      Text(
        text = "Become a better software engineer",
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
      )
      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "Learn data structures, algorithms, architecture and software design from first principles.",
        style = MaterialTheme.typography.body1,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
      )
      Spacer(modifier = Modifier.height(24.dp))
      ButtonsSection(
        onContinueWithGoogleClick = { onEvent(IntroViewEvent.OnContinueWithGoogleClick) },
        onLearnMoreClick = { onEvent(IntroViewEvent.OnLearnMoreClick) }
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
private fun ColumnScope.ButtonsSection(
  onContinueWithGoogleClick: () -> Unit,
  onLearnMoreClick: () -> Unit,
) {
  val density = LocalDensity.current
  var googleButtonWidth by remember { mutableStateOf(0.dp) }
  GoogleSignInButton(
    modifier = Modifier.onSizeChanged {
      googleButtonWidth = with(density) { it.width.toDp() }
    },
    onClick = onContinueWithGoogleClick,
  )
  Spacer(modifier = Modifier.height(8.dp))
  Text(
    text = "or",
    fontSize = 18.sp,
    fontWeight = FontWeight.Light,
    color = Gray,
  )
  Spacer(modifier = Modifier.height(8.dp))
  LearnMoreButton(
    modifier = Modifier.defaultMinSize(minWidth = googleButtonWidth),
    onClick = onLearnMoreClick
  )
}

@Composable
private fun LearnMoreButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  IvyButton(
    modifier = modifier,
    appearance = ButtonAppearance.Outlined(style = ButtonStyle.Primary),
    text = {
      Text("Learn more")
    },
    onClick = onClick,
  )
}

