package ui.screen.intro

class IntroViewState

sealed interface IntroViewEvent {
    data object OnContinueWithGoogleClick : IntroViewEvent
    data object OnLearnMoreClick : IntroViewEvent
}