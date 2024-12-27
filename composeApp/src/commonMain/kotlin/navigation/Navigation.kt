package navigation

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.StateFlow
import ui.screen.NotFoundPage
import ui.screen.home.HomeScreen

class Navigation(
  private val systemNavigation: SystemNavigation,
) {
  val currentPath: StateFlow<FullPath> = systemNavigation.currentPath

  @Composable
  fun NavHost() {
    val currentRoute by systemNavigation.currentRoute.collectAsState()
    val screen = remember(currentRoute) {
      Routing.resolve(currentRoute)?.also { it.initialize() }
    }
    DisposableEffect(screen) {
      // do nothing
      onDispose {
        screen?.destroy()
      }
    }
    screen?.Content() ?: NotFoundPage(currentRoute)
  }

  fun navigateTo(screen: Screen<*, *>) {
    systemNavigation.navigateTo(screen)
  }

  fun replaceWith(screen: Screen<*, *>) {
    systemNavigation.replaceWith(screen)
  }

  fun navigateBack() {
    if (!systemNavigation.navigateBack()) {
      navigateTo(HomeScreen())
    }
  }
}