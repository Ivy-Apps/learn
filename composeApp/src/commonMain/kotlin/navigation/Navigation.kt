package navigation

import Platform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import ui.screen.NotFoundPage

class Navigation(
    private val systemNavigation: SystemNavigation,
    private val platform: Platform,
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

    fun navigateBack() {
        systemNavigation.navigateBack()
    }
}