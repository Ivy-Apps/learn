package navigation

import domain.Session
import domain.SessionManager
import ui.screen.intro.IntroScreen

class AccessControl(
  private val navigation: Navigation,
  private val sessionManager: SessionManager,
) {
  suspend fun ensureLoggedIn(
    onDenied: (suspend () -> Unit)? = null,
    block: () -> Unit,
  ) {
    if (sessionManager.getSession() is Session.LoggedIn) {
      block()
    } else {
      onDenied?.invoke()
      navigation.navigateTo(IntroScreen())
    }
  }
}