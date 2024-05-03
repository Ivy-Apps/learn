package ui.screen.course

import androidx.compose.runtime.Composable
import ui.ComposeViewModel

class CourseViewModel : ComposeViewModel<CourseViewState, CourseViewEvent> {
    @Composable
    override fun viewState(): CourseViewState {
        return CourseViewState()
    }

    override fun onEvent(event: CourseViewEvent) {
        // TODO
    }
}