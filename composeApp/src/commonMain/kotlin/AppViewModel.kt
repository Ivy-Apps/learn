import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ivy.di.Di
import navigation.redirects.GoogleAuthRedirect
import navigation.redirects.LoggedOutUserRedirect
import util.Logger

class AppViewModel(
    private val logger: Logger,
) {

    private val redirects by lazy {
        listOf(
            Di.get<GoogleAuthRedirect>(),
            Di.get<LoggedOutUserRedirect>(),
        )
    }

    @Composable
    fun Init() {
        LaunchedEffect(Unit) {
            handleRedirects()
        }
    }

    private suspend fun handleRedirects() {
        redirects.firstOrNull { it.handle() }
            ?.let {
                logger.debug("Applied '${it.name}' redirect.")
            }
    }
}