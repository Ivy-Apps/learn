package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import ui.screen.NotFoundPage
import ui.screen.home.HomeScreen

class Navigation(
    private val systemNavigation: SystemNavigation,
) {
    @Composable
    fun NavHost() {
        val currentRoute by systemNavigation.currentRoute.collectAsState()
        val screen = remember(currentRoute) {
            Routing.resolve(currentRoute)?.also(Screen::initialize)
        }
        screen?.Content() ?: NotFoundPage(currentRoute)
    }

    fun navigateTo(screen: Screen) {
        systemNavigation.navigateTo(screen)
    }

    fun replaceWith(screen: Screen) {
        systemNavigation.replaceWith(screen)
    }

    fun navigateBack() {
        if (!systemNavigation.navigateBack()) {
            navigateTo(HomeScreen())
        }
    }
}