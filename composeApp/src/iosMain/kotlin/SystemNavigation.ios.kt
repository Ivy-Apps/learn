import ui.navigation.Screen

class IOSSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {}

    override fun navigateBack() {}
}

actual fun systemNavigation(): SystemNavigation = IOSSystemNavigation()