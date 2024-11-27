package navigation

import androidx.compose.runtime.Immutable
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import kotlinx.coroutines.flow.StateFlow

interface SystemNavigation {
    val currentRoute: StateFlow<Route>
    fun navigateTo(screen: Screen)
    fun replaceWith(screen: Screen)
    fun navigateBack()
}

@Immutable
data class Route(
    val path: String,
    val params: Map<String, String> = emptyMap(),
) {
    operator fun get(key: String): Option<String> {
        return params[key]?.some() ?: None
    }
}

expect fun systemNavigation(): SystemNavigation