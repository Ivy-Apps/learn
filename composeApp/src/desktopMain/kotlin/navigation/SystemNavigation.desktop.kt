package navigation

import kotlinx.coroutines.flow.StateFlow

class DesktopSystemNavigation : SystemNavigation {
    override val currentRoute: StateFlow<Route>
        get() = TODO("Not yet implemented")

    override fun navigateTo(screen: Screen) {}
    override fun replaceWith(screen: Screen) {
        TODO("Not yet implemented")
    }

    override fun navigateBack(): Boolean {
        TODO("Not yet implemented")
    }

}

actual fun systemNavigation(): SystemNavigation = DesktopSystemNavigation()