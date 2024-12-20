package ui.screen.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import domain.GoogleAuthenticationUseCase
import domain.analytics.Metrics
import ui.ComposeViewModel

class IntroViewModel(
  private val googleAuthenticationUseCase: GoogleAuthenticationUseCase,
  private val metrics: Metrics
) : ComposeViewModel<IntroViewState, IntroViewEvent> {
  @Composable
  override fun viewState(): IntroViewState {
    LaunchedEffect(Unit) {
      metrics.logMetric(name = "intro__view")
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