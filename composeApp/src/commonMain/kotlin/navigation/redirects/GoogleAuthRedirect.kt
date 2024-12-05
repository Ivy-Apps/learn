package navigation.redirects

import IvyConstants
import Platform
import domain.SessionManager
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.model.auth.SessionToken
import navigation.Navigation
import ui.screen.home.HomeScreen

class GoogleAuthRedirect(
    private val platform: Platform,
    private val sessionManager: SessionManager,
    private val analytics: Analytics,
    private val navigation: Navigation,
) : Redirect {
    override val name = "GoogleAuth"

    override suspend fun handle(): Boolean {
        val sessionTokenParam = platform.getUrlParam(IvyConstants.PARAM_SESSION_TOKEN)
        if (sessionTokenParam != null) {
            sessionManager.authenticate(SessionToken(sessionTokenParam))
            analytics.logEvent(
                source = Source.Intro,
                event = "continue_with_google"
            )
            navigation.replaceWith(HomeScreen())
            return true
        }
        return false
    }
}
