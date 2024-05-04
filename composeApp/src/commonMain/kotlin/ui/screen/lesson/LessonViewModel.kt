package ui.screen.lesson

import androidx.compose.runtime.Composable
import ui.ComposeViewModel

class LessonViewModel : ComposeViewModel<LessonViewState, LessonViewEvent> {
    @Composable
    override fun viewState(): LessonViewState {
        return LessonViewState()
    }

    override fun onEvent(event: LessonViewEvent) {
        // TODO
    }
}