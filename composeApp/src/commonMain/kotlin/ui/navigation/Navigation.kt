package ui.navigation

import SystemNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.collections.immutable.toPersistentList

class Navigation(private val systemNavigation: SystemNavigation) {
    private var backstack by mutableStateOf(persistentListOf<Screen>())

    @Composable
    fun NavHost() {
        val currentScreen = remember(backstack) { backstack.lastOrNull() }
        currentScreen?.Content()
    }

    fun backstack(): List<Screen> = backstack

    fun navigate(screen: Screen) {
        backstack = backstack.plus(screen.also(Screen::initialize))
        systemNavigation.navigateTo(screen)
    }

    fun backUntil(predicate: (Screen) -> Boolean) {
        var lastScreen: Screen?
        do {
            lastScreen = back()
        } while (lastScreen != null && !predicate(lastScreen))
    }

    fun back(): Screen? {
        val lastScreen = backstack.lastOrNull()
        backstack = backstack.dropLast(1).toPersistentList()
        return lastScreen?.also {
            it.destroy()
//            systemNavigation.navigateBack()
        }
    }
}