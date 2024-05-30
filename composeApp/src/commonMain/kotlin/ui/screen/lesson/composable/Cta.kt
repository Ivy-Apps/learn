package ui.screen.lesson.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.button.CtaButton
import ui.screen.lesson.CtaViewState
import ui.screen.lesson.LessonItemIdViewState

@Composable
fun CtaBar(
    viewState: CtaViewState,
    onContinueClick: (LessonItemIdViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(bottom = 16.dp, top = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (viewState) {
            is CtaViewState.Continue -> ContinueButton(
                onClick = { onContinueClick(viewState.currentItemId) }
            )
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
        text = "CONTINUE",
        onClick = onClick,
    )
}