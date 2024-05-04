package ui.screen.course.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.screen.course.ProgressViewState
import ui.theme.Gray
import ui.theme.Green

@Composable
fun LessonArrow(
    progress: ProgressViewState,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier.size(64.dp),
        imageVector = Icons.Filled.KeyboardArrowDown,
        contentDescription = null,
        tint = when (progress) {
            ProgressViewState.Completed -> Green
            ProgressViewState.Current -> MaterialTheme.colors.primary
            ProgressViewState.Upcoming -> Gray
        }
    )
}

