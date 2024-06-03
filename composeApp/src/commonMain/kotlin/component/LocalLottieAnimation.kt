package component

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import ivy.data.LottieAnimationLoader
import ivy.di.Di
import learn.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * @param animationFile - the name of the animation file in the "composeResources/files" folder.
 * For example, "intro_lottie_anim.json".
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun LocalLottieAnimation(
    animationFile: String,
    repeat: Boolean,
    modifier: Modifier = Modifier,
) {
    var lottieJson by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        lottieJson = Res.readBytes("files/$animationFile").decodeToString()
    }
    lottieJson?.let {
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(it))
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            iterations = if (repeat) Int.MAX_VALUE else 1,
        )
    }
}

@Composable
fun RemoteLottieAnimation(
    animationUrl: String,
    repeat: Boolean,
    modifier: Modifier = Modifier,
) {
    var lottieJson by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        Di.get<LottieAnimationLoader>().loadJson(animationUrl)
            .onRight { json ->
                lottieJson = json
            }
    }
    lottieJson?.let {
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(it))
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            iterations = if (repeat) Int.MAX_VALUE else 1,
        )
    }
}