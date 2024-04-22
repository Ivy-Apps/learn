package navigation

import androidx.compose.runtime.*

class Navigation {
    private var backstack by mutableStateOf(emptyList<Screen>())

    @Composable
    fun NavHost() {
        val currentScreen = remember(backstack) { backstack.lastOrNull() }
        currentScreen?.Content()
    }

    fun navigate(screen: Screen) {
        backstack = backstack + screen.also(Screen::initialize)
    }

    fun backUntil(predicate: (Screen) -> Boolean) {
        var lastScreen: Screen?
        do {
            lastScreen = back()
        } while (lastScreen != null && predicate(lastScreen))
    }

    fun back(): Screen? {
        val lastScreen = backstack.lastOrNull()
        backstack = backstack.dropLast(1)
        return lastScreen?.also(Screen::destroy)
    }
}