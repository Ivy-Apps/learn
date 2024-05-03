package ui.screen.home.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.text.Title
import ui.screen.home.HomeItemViewState
import ui.theme.Gray

@Composable
fun Section(
    section: HomeItemViewState.Section,
    modifier: Modifier = Modifier
) {
    Title(
        modifier = modifier,
        text = section.title,
        color = Gray
    )
}