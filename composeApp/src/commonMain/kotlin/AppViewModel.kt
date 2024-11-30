import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import domain.SessionManager
import ivy.model.auth.SessionToken
import navigation.Navigation
import ui.screen.home.HomeScreen

class AppViewModel(
    private val sessionManager: SessionManager,
    private val platform: Platform,
    private val navigation: Navigation,
) {

    @Composable
    fun Init() {
        LaunchedEffect(Unit) {
            initialize()
        }
    }

    private suspend fun initialize() {
        if (sessionManager.getSession() != null) {
            // already logged
            navigation.replaceWith(HomeScreen())
            return
        }

        val sessionTokenParam = platform.getUrlParam(IvyConstants.SessionTokenParam)
        if (sessionTokenParam != null) {
            sessionManager.authenticate(SessionToken(sessionTokenParam))
            navigation.replaceWith(HomeScreen())
        }
    }
}