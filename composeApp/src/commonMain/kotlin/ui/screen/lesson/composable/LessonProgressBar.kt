package ui.screen.lesson.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import ui.screen.lesson.LessonProgressViewState
import ui.theme.Gray


@Composable
fun LessonProgressBar(
    viewState: LessonProgressViewState,
    modifier: Modifier = Modifier,
    progressBarWidth: Dp = 164.dp,
) {
    Box(modifier = modifier) {
        val progressPercent by animateFloatAsState(
            targetValue = viewState.done / viewState.total.toFloat(),
        )
        // background (total)
        ProgressBarLine(width = progressBarWidth, color = Gray)
        // foreground (progress)
        val progressWidth = (progressBarWidth * progressPercent).coerceAtMost(progressBarWidth)
        ProgressBarLine(width = progressWidth, color = MaterialTheme.colors.secondary)
    }
}

@Composable
private fun ProgressBarLine(
    width: Dp,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier.height(8.dp)
            .width(width)
            .background(color, RoundedCornerShape(percent = 50))
    )
}