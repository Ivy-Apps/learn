package ui.screen.lesson.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.button.CtaButton
import ui.screen.lesson.CtaViewState
import ui.screen.lesson.LessonItemIdViewState

@Composable
fun Cta(
    viewState: CtaViewState,
    onContinueClick: (LessonItemIdViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (viewState) {
        is CtaViewState.Continue -> ContinueButton(
            modifier = modifier,
            onClick = { onContinueClick(viewState.currentItemId) }
        )
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