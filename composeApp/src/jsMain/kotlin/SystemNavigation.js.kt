import kotlinx.browser.window
import ui.navigation.Screen

class WebSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {
        // TODO: Temporary workaround until we support deep links
        val path = "" // "/${screen.path}"

        val stateObject = js("({})")
        // TODO: Temporary workaround until we support deep links
        stateObject.screen = "" //screen.path
        window.history.pushState(stateObject, "", path)
    }

    override fun navigateBack() {
        // Check if there's more than one entry in the history stack
        if (window.history.length > 1) {
            window.history.back()
        } else {
            // Handle what happens if there's no history to go back to
            // Perhaps navigate to a default screen or show a message
        }
    }
}

actual fun systemNavigation(): SystemNavigation = WebSystemNavigation()