package navigation

import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WebSystemNavigation : SystemNavigation {

    // Initialize with the current route
    private val _routeChange = MutableStateFlow(getCurrentRouteInfo())
    override val currentRoute: StateFlow<Route> = _routeChange

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
        window.history.pushState(js("({})"), "", screen.toFullPath())
        emitCurrentRoute() // Update the route immediately
    }

    override fun replaceWith(screen: Screen) {
        window.history.replaceState(js("({})"), "", screen.toFullPath())
        emitCurrentRoute()
    }

    private fun Screen.toFullPath(): String {
        val route = toRoute()
        val params = buildString {
            for ((key, value) in route.params) {
                append(if (isEmpty()) '?' else '&')
                append("$key=$value")
            }
        }
        return "${route.path}$params"
    }

    override fun navigateBack() {
        window.history.back() // Let the browser handle back navigation
        // No need to call emitCurrentRoute() here; the listener will handle it
    }

    private fun emitCurrentRoute() {
        val routeInfo = getCurrentRouteInfo()
        _routeChange.value = routeInfo // Update the StateFlow with the new route
    }

    private fun getCurrentRouteInfo(): Route {
        val route = window.location.pathname.trimStart('/')
        val params = parseParams(window.location.search)
        return Route(route, params)
    }

    private fun parseParams(query: String): Map<String, String> {
        if (query.isEmpty() || query == "?") return emptyMap()

        return query.trimStart('?')
            .split("&")
            .mapNotNull {
                val parts = it.split("=")
                if (parts.size == 2) parts[0] to parts[1] else null
            }
            .toMap()
    }
}


actual fun systemNavigation(): SystemNavigation = WebSystemNavigation()