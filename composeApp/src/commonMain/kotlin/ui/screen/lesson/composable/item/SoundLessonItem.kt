package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.button.SecondaryButton
import ui.screen.lesson.SoundItemViewState

@Composable
fun SoundLessonItem(
    viewState: SoundItemViewState,
    onClick: (soundUrl: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SecondaryButton(
        modifier = modifier.padding(top = 12.dp),
        text = viewState.text,
        onClick = { onClick(viewState.soundUrl) },
    )
}