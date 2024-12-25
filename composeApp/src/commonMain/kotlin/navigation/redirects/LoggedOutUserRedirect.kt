package navigation.redirects

import AppConfiguration
import domain.Session
import domain.SessionManager
import navigation.Navigation
import ui.screen.intro.IntroScreen

class LoggedOutUserRedirect(
  private val sessionManager: SessionManager,
  private val navigation: Navigation,
  private val appConfiguration: AppConfiguration,
) : Redirect {
  override val name = "Logged-out user"

  override suspend fun handle(): Boolean {
    if (sessionManager.getSession() is Session.LoggedOut && !appConfiguration.fakesEnabled) {
      navigation.replaceWith(IntroScreen())
      return true
    }
    return false
  }
}