package navigation.redirects

import domain.Session
import domain.SessionManager
import navigation.Navigation
import ui.screen.intro.IntroScreen

class LoggedOutUserRedirect(
  private val sessionManager: SessionManager,
  private val navigation: Navigation,
  private val loggedInRedirect: LoggedInRedirect,
) : Redirect {
  override val name = "Logged-out user"

  override suspend fun handle(): Boolean {
    if (sessionManager.getSession() is Session.LoggedOut) {
      val currentPath = navigation.currentPath.value
      navigation.replaceWith(IntroScreen(showLearnMore = true))
      loggedInRedirect.prepare(currentPath)
      return true
    }
    return false
  }
}