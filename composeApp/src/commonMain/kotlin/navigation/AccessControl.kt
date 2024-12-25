package navigation

import domain.Session
import domain.SessionManager
import ui.screen.intro.IntroScreen

class AccessControl(
  private val navigation: Navigation,
  private val sessionManager: SessionManager,
) {
  suspend fun ensureLoggedIn(block: () -> Unit) {
    if (sessionManager.getSession() is Session.LoggedIn) {
      block()
    } else {
      navigation.navigateTo(IntroScreen())
    }
  }
}