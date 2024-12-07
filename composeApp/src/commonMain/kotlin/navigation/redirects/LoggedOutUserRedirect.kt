package navigation.redirects

import domain.SessionManager
import navigation.Navigation
import ui.screen.intro.IntroScreen

class LoggedOutUserRedirect(
    private val sessionManager: SessionManager,
    private val navigation: Navigation,
) : Redirect {
    override val name = "Logged-out user"

    override suspend fun handle(): Boolean {
        if (sessionManager.getSessionOrNull() == null) {
            navigation.replaceWith(IntroScreen())
            return true
        }
        return false
    }
}