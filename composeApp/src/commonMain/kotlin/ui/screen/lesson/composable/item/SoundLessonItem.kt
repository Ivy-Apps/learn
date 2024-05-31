package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.button.SecondaryButton
import ui.screen.lesson.SoundItemViewState
import ui.screen.lesson.composable.ItemSpacing

@Composable
fun SoundLessonItem(
    viewState: SoundItemViewState,
    onClick: (soundUrl: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SecondaryButton(
        modifier = modifier.padding(top = ItemSpacing),
        text = viewState.text,
        icon = Icons.Default.PlayArrow,
        onClick = { onClick(viewState.soundUrl) },
    )
}