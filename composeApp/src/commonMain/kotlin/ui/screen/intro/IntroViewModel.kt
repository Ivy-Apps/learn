package ui.screen.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import domain.GoogleAuthenticationUseCase
import domain.SessionManager
import domain.analytics.Analytics
import domain.analytics.Source
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import navigation.Navigation
import ui.ComposeViewModel
import ui.screen.home.HomeScreen

class IntroViewModel(
  private val googleAuthenticationUseCase: GoogleAuthenticationUseCase,
  private val sessionManager: SessionManager,
  private val analytics: Analytics,
  private val scope: CoroutineScope,
  private val navigation: Navigation,
  private val args: IntroScreen.Args,
) : ComposeViewModel<IntroViewState, IntroViewEvent> {
  @Composable
  override fun viewState(): IntroViewState {
    LaunchedEffect(Unit) {
      analytics.logEvent(
        source = Source.Intro,
        event = "intro__view"
      )
    }
    return IntroViewState(
      showLearnMore = getShowLearnMore()
    )
  }

  @Composable
  fun getShowLearnMore(): Boolean = args.showLearnMore

  override fun onEvent(event: IntroViewEvent) {
    when (event) {
      IntroViewEvent.OnContinueWithGoogleClick -> handleContinueClick()
      IntroViewEvent.OnLearnMoreClick -> handleLearnMoreClick()
    }
  }

  private fun handleContinueClick() {
    googleAuthenticationUseCase.loginWithGoogle()
  }

  private fun handleLearnMoreClick() {
    scope.launch {
      sessionManager.startAnonymousSession()
      navigation.replaceWith(HomeScreen())
      analytics.logEvent(
        source = Source.Intro,
        event = "click_learn_more"
      )
    }
  }
}