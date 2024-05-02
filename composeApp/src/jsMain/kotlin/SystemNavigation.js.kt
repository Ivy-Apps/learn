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
        window.history.back()
    }
}

actual fun systemNavigation(): SystemNavigation = WebSystemNavigation()