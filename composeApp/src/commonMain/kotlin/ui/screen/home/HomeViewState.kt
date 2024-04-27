package ui.screen.home

class HomeViewState

sealed interface HomeViewEvent {
    data object OnBackClick : HomeViewEvent
    data object OnColorsDemoClick : HomeViewEvent
}