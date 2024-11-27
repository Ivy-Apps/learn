package domain.fake

import domain.GoogleAuthenticationUseCase
import navigation.Navigation
import ui.screen.home.HomeScreen

class FakeGoogleAuthenticationUseCase(
    private val navigation: Navigation
) : GoogleAuthenticationUseCase {
    override fun loginWithGoogle() {
        navigation.navigateTo(HomeScreen())
    }
}