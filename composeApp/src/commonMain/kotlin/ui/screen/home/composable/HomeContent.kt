package ui.screen.home.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import ui.screen.home.HomeItemViewState
import ui.screen.home.HomeViewEvent
import ui.screen.home.HomeViewState

@Composable
fun HomeContent(
    viewState: HomeViewState,
    onEvent: (HomeViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(
            onBackClick = {
                onEvent(HomeViewEvent.OnBackClick)
            }
        ),
        title = "Learn"
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(viewState.items, key = {
                "$it"
            }) {
                when (it) {
                    is HomeItemViewState.Course -> CourseCard(course = it, onEvent = onEvent)
                    is HomeItemViewState.Section -> Section(section = it)
                }
            }
        }
    }
}