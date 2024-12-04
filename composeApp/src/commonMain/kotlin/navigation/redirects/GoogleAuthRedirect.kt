package navigation.redirects

import IvyConstants
import Platform
import domain.SessionManager
import ivy.model.auth.SessionToken
import navigation.Navigation
import ui.screen.home.HomeScreen

class GoogleAuthRedirect(
    private val platform: Platform,
    private val sessionManager: SessionManager,
    private val navigation: Navigation,
) : Redirect {
    override suspend fun handle(): Boolean {
        val sessionTokenParam = platform.getUrlParam(IvyConstants.PARAM_SESSION_TOKEN)
        if (sessionTokenParam != null) {
            sessionManager.authenticate(SessionToken(sessionTokenParam))
            navigation.replaceWith(HomeScreen())
            return true
        }
        return false
    }

}