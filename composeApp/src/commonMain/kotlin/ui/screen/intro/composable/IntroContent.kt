package ui.screen.intro.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import learn.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
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
        IntroAnimation()
        Text(text = "Hello!")
        Text(text = "Learn programming by thinking.")
        Button(onClick = {
            onEvent(IntroViewEvent.OnContinueClick)
        }) {
            Text("Let's go")
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun IntroAnimation() {
    var lottieJson by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        lottieJson = Res.readBytes("files/intro_lottie_anim.json").decodeToString()
    }
    lottieJson?.let {
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(it))
        LottieAnimation(composition = composition)
    }
}