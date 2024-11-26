package ui.screen.intro

import androidx.compose.runtime.Composable
import domain.GoogleAuthenticationUseCase
import ui.ComposeViewModel

class IntroViewModel(
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase,
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
        googleAuthenticationUseCase.loginWithGoogle()
    }
}