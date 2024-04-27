package ui.screen.home

import androidx.compose.runtime.Composable
import ui.ComposeViewModel
import ui.navigation.Navigation
import ui.screen.debug.ColorDemoScreen

class HomeViewModel(
    private val navigation: Navigation
) : ComposeViewModel<HomeViewState, HomeViewEvent> {
    @Composable
    override fun viewState(): HomeViewState {
        return HomeViewState()
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.OnBackClick -> handleBackClick()
            HomeViewEvent.OnColorsDemoClick -> handleColorsDemoClick()
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }

    private fun handleColorsDemoClick() {
        navigation.navigate(ColorDemoScreen())
    }
}