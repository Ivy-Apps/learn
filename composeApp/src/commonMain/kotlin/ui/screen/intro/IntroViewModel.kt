package ui.screen.intro

import IvyConstants
import Platform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import domain.GoogleAuthenticationUseCase
import ui.ComposeViewModel
import ui.navigation.Navigation
import ui.screen.home.HomeScreen

class IntroViewModel(
    private val navigation: Navigation,
    private val platform: Platform,
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase,
) : ComposeViewModel<IntroViewState, IntroViewEvent> {
    @Composable
    override fun viewState(): IntroViewState {
        LaunchedEffect(Unit) {
            platform.getUrlParam(IvyConstants.SessionTokenParam)
                ?.let { sessionToken ->
                    navigation.navigate(HomeScreen())
                }
        }
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