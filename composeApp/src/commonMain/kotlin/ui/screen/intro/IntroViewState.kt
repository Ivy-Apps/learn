package ui.screen.intro

class IntroViewState

sealed interface IntroViewEvent {
    data object OnContinueWithGoogleClick : IntroViewEvent
}