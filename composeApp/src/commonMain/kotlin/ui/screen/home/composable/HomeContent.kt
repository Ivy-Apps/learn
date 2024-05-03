package ui.screen.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import ivy.model.CourseId
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
            modifier = Modifier.padding(contentPadding),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 48.dp, start = 24.dp, end = 24.dp)
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
                    is HomeItemViewState.Course -> CourseCard(
                        course = it,
                        onCourseClick = {
                            onEvent(HomeViewEvent.OnCourseClick(CourseId(it.id)))
                        })

                    is HomeItemViewState.Section -> Section(
                        modifier = Modifier.padding(top = 16.dp),
                        section = it
                    )
                }
            }
        }
    }
}