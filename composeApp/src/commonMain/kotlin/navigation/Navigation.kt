package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

class Navigation(
    private val systemNavigation: SystemNavigation
) {
    @Composable
    fun NavHost() {
        val currentRoute by systemNavigation.currentRoute.collectAsState()
        val screen = remember(currentRoute) {
            Routing.resolve(currentRoute)?.also(Screen::initialize)
        }
        screen?.Content()
    }

    fun navigateTo(screen: Screen) {
        systemNavigation.navigateTo(screen)
    }

    fun navigateBack() {
        systemNavigation.navigateBack()
    }
}