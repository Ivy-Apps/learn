package navigation

import ivy.di.Di
import kotlinx.browser.window
import kotlinx.coroutines.flow.*
import util.AppScope

class WebSystemNavigation(
  private val appScope: AppScope,
) : SystemNavigation {

  // Initialize with the current route
  private val _pathChange = MutableStateFlow(getCurrentPath())
  override val currentPath: StateFlow<FullPath> = _pathChange

  override val currentRoute: StateFlow<Route>
    get() = currentPath.map { it.toRoute() }
      .stateIn(
        scope = appScope.get,
        started = SharingStarted.Eagerly,
        initialValue = currentPath.value.toRoute(),
      )

  init {
    setupRouteChangeListener()
  }


  private fun setupRouteChangeListener() {
    // Listen for back/forward navigation using addEventListener
    window.addEventListener("popstate", {
      emitCurrentRoute()
    })
    // Emit the initial route on startup is already handled by initializing _routeChange
  }

  override fun navigateTo(screen: Screen) {
    navigateTo(screen.toFullPath())
  }

  override fun navigateTo(path: FullPath) {
    window.history.pushState(js("({})"), "", path.value)
    emitCurrentRoute()
  }

  override fun replaceWith(screen: Screen) {
    replaceWith(screen.toFullPath())
  }

  override fun replaceWith(path: FullPath) {
    window.history.replaceState(js("({})"), "", path.value)
    emitCurrentRoute()
  }

  private fun Screen.toFullPath(): FullPath {
    val route = toRoute()
    val params = buildString {
      for ((key, value) in route.params) {
        append(if (isEmpty()) '?' else '&')
        append("$key=$value")
      }
    }
    return FullPath("${route.path}$params")
  }

  override fun navigateBack(): Boolean {
    if (window.history.length <= 1) return false

    window.history.back() // Let the browser handle back navigation
    // No need to call emitCurrentRoute() here; the listener will handle it
    return true
  }

  private fun emitCurrentRoute() {
    _pathChange.value = getCurrentPath()
  }

  private fun getCurrentPath(): FullPath {
    val route = window.location.pathname.trimStart('/')
    return FullPath(value = route + '?' + window.location.search)
  }

  private fun FullPath.toRoute(): Route {
    val (path, search) = value.split("?")
    return Route(
      path = path,
      params = parseParams(search)
    )
  }

  private fun parseParams(query: String): Map<String, String> {
    if (query.isEmpty() || query == "?") return emptyMap()

    return query.trimStart('?')
      .split("&")
      .mapNotNull {
        val parts = it.split("=")
        if (parts.size == 2) {
          val key = decodeURIComponent(parts[0])
          val value = decodeURIComponent(parts[1])
          key to value
        } else null
      }
      .toMap()
  }

  private fun decodeURIComponent(encoded: String): String {
    return js("decodeURIComponent")(encoded) as String
  }
}


actual fun systemNavigation(): SystemNavigation = WebSystemNavigation(
  appScope = Di.get(),
)