package ui.screen.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import domain.GoogleAuthenticationUseCase
import domain.analytics.Analytics
import domain.analytics.Source
import ui.ComposeViewModel

class IntroViewModel(
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase,
    private val analytics: Analytics,
) : ComposeViewModel<IntroViewState, IntroViewEvent> {
    @Composable
    override fun viewState(): IntroViewState {
        LaunchedEffect(Unit) {
            analytics.logEvent(
                source = Source.Intro,
                event = "view",
            )
        }
        return IntroViewState()
    }

    override fun onEvent(event: IntroViewEvent) {
        when (event) {
            IntroViewEvent.OnContinueWithGoogleClick -> handleContinueClick()
        }
    }

    private fun handleContinueClick() {
        googleAuthenticationUseCase.loginWithGoogle()
    }
}