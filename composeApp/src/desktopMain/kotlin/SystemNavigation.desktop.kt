import ui.navigation.Screen

class DesktopSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {}
    override fun navigateBack() {}
    override fun setupUrlChangeListener(onUrlChange: (String, Map<String, String>) -> Unit) {}
}

actual fun systemNavigation(): SystemNavigation = DesktopSystemNavigation()