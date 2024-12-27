package navigation

import androidx.compose.runtime.Immutable
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import kotlinx.coroutines.flow.StateFlow
import kotlin.jvm.JvmInline

interface SystemNavigation {
  val currentRoute: StateFlow<Route>
  val currentPath: StateFlow<FullPath>

  fun navigateTo(screen: Screen)
  fun navigateTo(path: FullPath)
  fun replaceWith(screen: Screen)
  fun replaceWith(path: FullPath)
  fun navigateBack(): Boolean
}

@Immutable
@JvmInline
value class FullPath(val value: String)

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