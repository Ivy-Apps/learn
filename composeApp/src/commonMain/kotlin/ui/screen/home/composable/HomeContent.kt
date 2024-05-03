package ui.screen.home.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
        LazyVerticalGrid(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(
                items = viewState.items,
                span = {
                    when (it) {
                        is HomeItemViewState.Course -> GridItemSpan(1)
                        is HomeItemViewState.Section -> GridItemSpan(2)
                    }
                },
                key = {
                    when (it) {
                        is HomeItemViewState.Course -> it.id
                        is HomeItemViewState.Section -> it.title
                    }
                }
            ) {
                when (it) {
                    is HomeItemViewState.Course -> {
                        Spacer(Modifier.height(12.dp))
                        CourseCard(course = it, onEvent = onEvent)
                    }

                    is HomeItemViewState.Section -> {
                        Spacer(Modifier.height(16.dp))
                        Section(section = it)
                    }
                }
            }
        }
    }
}