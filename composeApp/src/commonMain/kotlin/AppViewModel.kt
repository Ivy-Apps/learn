import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ivy.di.Di
import navigation.redirects.GoogleAuthRedirect
import navigation.redirects.LoggedOutUserRedirect

class AppViewModel {

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
        for (redirect in redirects) {
            if (redirect.handle()) {
                // redirect applied, do nothing
                return
            }
        }
    }
}