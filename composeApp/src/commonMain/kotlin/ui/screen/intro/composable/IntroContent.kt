package ui.screen.intro.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.LocalLottieAnimation
import component.ScreenType.*
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import component.screenType
import ui.screen.intro.IntroViewEvent
import ui.screen.intro.IntroViewState
import ui.theme.Gray
import ui.theme.IvyTheme


@Composable
fun IntroContent(
  viewState: IntroViewState,
  onEvent: (IntroViewEvent) -> Unit
) {
  Surface {
    Column(
      modifier = Modifier.fillMaxSize()
        .padding(
          horizontal = when (screenType()) {
            Mobile -> 24.dp
            Tablet -> 48.dp
            Desktop -> 64.dp
          }
        ),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
      ) {
        item {
          IntroAnimation()
          Spacer(modifier = Modifier.height(24.dp))
        }
        item {
          Text(
            text = "Become a better software engineer",
            style = IvyTheme.typography.h2,
            fontWeight = FontWeight.SemiBold
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Learn data structures, algorithms, architecture and software design from first principles.",
            style = IvyTheme.typography.b1,
            fontWeight = FontWeight.Medium,
          )
        }
        item {
          Spacer(modifier = Modifier.height(48.dp))
          ButtonsSection(
            onContinueWithGoogleClick = { onEvent(IntroViewEvent.OnContinueWithGoogleClick) },
            onLearnMoreClick = { onEvent(IntroViewEvent.OnLearnMoreClick) }
          )
          Spacer(Modifier.height(48.dp))
        }
      }
      IntroLegalText()
      Spacer(Modifier.height(24.dp))
    }
  }
}

@Composable
private fun IntroAnimation(
  modifier: Modifier = Modifier,
) {
  LocalLottieAnimation(
    modifier = modifier
      .padding(horizontal = 24.dp)
      .sizeIn(
        maxWidth = when (screenType()) {
          Mobile -> 360.dp
          Tablet -> 320.dp
          Desktop -> 400.dp
        }
      )
      .fillMaxWidth()
      .aspectRatio(1f, matchHeightConstraintsFirst = true),
    animationFile = "intro_lottie_anim.json",
    repeat = true
  )
}

@Composable
private fun ButtonsSection(
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
  Spacer(modifier = Modifier.height(7.dp))
  Text(
    text = "or",
    style = IvyTheme.typography.b2,
    fontWeight = FontWeight.Medium,
    color = Gray,
  )
  Spacer(modifier = Modifier.height(4.dp))
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