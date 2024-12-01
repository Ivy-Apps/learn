package ui.screen.intro.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.LocalLottieAnimation
import component.platformHorizontalPadding
import component.text.Headline
import component.text.Title
import learn.composeapp.generated.resources.Res
import learn.composeapp.generated.resources.google_signin_logo
import org.jetbrains.compose.resources.painterResource
import ui.screen.intro.IntroViewEvent
import ui.screen.intro.IntroViewState
import ui.theme.RobotoFontFamily


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
            LocalLottieAnimation(
                modifier = Modifier.size(400.dp),
                animationFile = "intro_lottie_anim.json",
                repeat = true
            )
            Headline(text = "Hello!")
            Spacer(modifier = Modifier.height(12.dp))
            Title(text = "Learn programming by thinking.")
            Spacer(modifier = Modifier.height(24.dp))
            GoogleButton(
                onClick = {
                    onEvent(IntroViewEvent.OnContinueWithGoogleClick)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            IntroLegalText()
        }
    }
}

@Composable
private fun GoogleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(percent = 50)
    val isLight = MaterialTheme.colors.isLight
    Row(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = if (isLight) {
                    Color(0xFF747775)
                } else {
                    Color(0xFF8E918F)
                },
                shape = shape,
            )
            .background(
                color = if (isLight) {
                    Color(0xFFFFFFFF)
                } else {
                    Color(0xFF131314)
                },
                shape = shape,
            )
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(Res.drawable.google_signin_logo),
            contentDescription = null,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Sign in with Google",
            color = if (isLight) {
                Color(0xFF1F1F1F)
            } else {
                Color(0xFFE3E3E3)
            },
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = RobotoFontFamily,
            textAlign = TextAlign.Center,
        )
    }
}