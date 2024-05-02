import ui.navigation.Screen

class AndroidSystemNavigation : SystemNavigation {
    override fun navigateTo(screen: Screen) {}
    override fun navigateBack() {}
}

actual fun systemNavigation(): SystemNavigation = AndroidSystemNavigation()