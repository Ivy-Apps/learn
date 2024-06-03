package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.RemoteLottieAnimation
import ui.screen.lesson.LottieAnimationItemViewState

@Composable
fun LottieAnimationLessonItem(
    viewState: LottieAnimationItemViewState,
    modifier: Modifier = Modifier,
) {
    RemoteLottieAnimation(
        modifier = modifier.size(232.dp),
        animationUrl = viewState.lottieUrl,
        repeat = true,
    )
}