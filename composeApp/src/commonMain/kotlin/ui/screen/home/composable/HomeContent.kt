package ui.screen.home.composable

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import component.BackButton
import component.LearnScaffold
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
    ) {
        Button(onClick = {
            onEvent(HomeViewEvent.OnColorsDemoClick)
        }) {
            Text("Colors demo")
        }
    }
}