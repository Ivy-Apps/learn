import ui.navigation.Screen

interface SystemNavigation {
    fun navigateTo(screen: Screen)
    fun navigateBack()
}

expect fun systemNavigation(): SystemNavigation