import ui.navigation.Screen

class DesktopSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {}
    override fun navigateBack() {}
}

actual fun systemNavigation(): SystemNavigation = DesktopSystemNavigation()