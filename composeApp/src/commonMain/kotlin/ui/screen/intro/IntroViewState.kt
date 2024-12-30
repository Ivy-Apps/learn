package ui.screen.intro

data class IntroViewState(
    val showLearnMore: Boolean,
)

sealed interface IntroViewEvent {
    data object OnContinueWithGoogleClick : IntroViewEvent
    data object OnLearnMoreClick : IntroViewEvent
}