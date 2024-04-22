package ui

import androidx.compose.runtime.Composable

interface ComposeViewModel<ViewState, ViewEvent> {

    @Composable
    fun viewState(): ViewState

    fun onEvent(event: ViewEvent)
}