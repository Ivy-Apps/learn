package navigation.redirects

import IvyConstants
import Platform
import domain.SessionManager
import domain.analytics.Analytics
import domain.analytics.Source
import ivy.model.auth.SessionToken

class GoogleAuthRedirect(
    private val platform: Platform,
    private val sessionManager: SessionManager,
    private val analytics: Analytics,
) : Redirect {
    override val name = "GoogleAuth"

    override suspend fun handle(): Boolean {
        val sessionTokenParam = platform.getUrlParam(IvyConstants.PARAM_SESSION_TOKEN)
        if (sessionTokenParam != null) {
            sessionManager.login(SessionToken(sessionTokenParam))
            analytics.logEvent(
                source = Source.Intro,
                event = "continue_with_google"
            )
            return true
        }
        return false
    }
}
