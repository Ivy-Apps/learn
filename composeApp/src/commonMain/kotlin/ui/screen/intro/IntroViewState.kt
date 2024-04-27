package ui.screen.intro

data class IntroViewState(
    val info: String
)

sealed interface IntroViewEvent {
    data object BackClick : IntroViewEvent
}