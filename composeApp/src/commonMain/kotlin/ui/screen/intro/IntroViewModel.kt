package ui.screen.intro

import androidx.compose.runtime.Composable
import ui.ComposeViewModel
import ui.navigation.Navigation
import ui.screen.home.HomeScreen

class IntroViewModel(
    private val navigation: Navigation
) : ComposeViewModel<IntroViewState, IntroViewEvent> {
    @Composable
    override fun viewState(): IntroViewState {
        return IntroViewState()
    }

    override fun onEvent(event: IntroViewEvent) {
        when (event) {
            IntroViewEvent.OnContinueClick -> handleContinueClick()
        }
    }

    private fun handleContinueClick() {
        navigation.navigate(HomeScreen())
    }
}