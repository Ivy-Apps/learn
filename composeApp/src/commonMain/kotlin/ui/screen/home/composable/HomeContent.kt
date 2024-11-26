package ui.screen.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.*
import component.button.SecondaryButton
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
        title = "Learn",
        actions = {
            // TODO - update button
            SecondaryButton(
                text = "",
                icon = Icons.Filled.Settings,
                onClick = {
                    onEvent(HomeViewEvent.OnSettingsClick)
                }
            )
        }
    ) { contentPadding ->
        val columnsCount = when (screenType()) {
            ScreenType.Mobile -> 1
            ScreenType.Tablet -> 2
            ScreenType.Desktop -> 3
        }
        val horizontalPadding = platformHorizontalPadding()
        LazyVerticalGrid(
            modifier = Modifier.padding(contentPadding),
            columns = GridCells.Fixed(
                count = columnsCount
            ),
            verticalArrangement = Arrangement.spacedBy(
                if (isLargeScreen()) 16.dp else 12.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(
                if (isLargeScreen()) 24.dp else 12.dp
            ),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 48.dp,
                start = horizontalPadding,
                end = horizontalPadding
            )
        ) {
            items(
                items = viewState.items,
                span = {
                    when (it) {
                        is HomeItemViewState.Course -> GridItemSpan(1)
                        is HomeItemViewState.Section -> GridItemSpan(columnsCount)
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
                            onEvent(
                                HomeViewEvent.OnCourseClick(
                                    id = CourseId(it.id),
                                    name = it.name
                                )
                            )
                        })

                    is HomeItemViewState.Section -> Section(
                        modifier = Modifier.padding(top = 12.dp),
                        section = it
                    )
                }
            }
        }
    }
}