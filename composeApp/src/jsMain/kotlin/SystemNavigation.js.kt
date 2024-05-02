import kotlinx.browser.window
import ui.navigation.Screen

class WebSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {
        // Construct the path based on the screen's path
        val path = "/${screen.path}"

        // Create a state object as a dynamic object
        val stateObject = js("({})")
        stateObject.screen = screen.path

        // Use the history API to push the new state
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