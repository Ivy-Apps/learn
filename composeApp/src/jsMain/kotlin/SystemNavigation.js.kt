import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ui.navigation.Screen

class WebSystemNavigation : SystemNavigation {

    private val _routeChange = MutableSharedFlow<RouteInfo>(replay = 1)
    override val routeChange: SharedFlow<RouteInfo> = _routeChange

    private val scope = MainScope() // Coroutine scope for emitting Flow updates

    init {
        setupUrlChangeListener()
    }

    override fun navigateTo(screen: Screen) {
        val path = "/${screen.toRoute()}"
        val params = "" // Add query params if needed in the future
        val fullPath = "$path$params"

        window.history.pushState(js("({})"), "", fullPath)
        emitCurrentRoute() // Emit route change for immediate updates
    }

    override fun navigateBack() {
        window.history.back() // Let the browser handle back navigation
    }

    private fun setupUrlChangeListener() {
        // Listen for back/forward navigation
        window.onpopstate = {
            emitCurrentRoute()
        }

        // Emit the initial route on startup
        emitCurrentRoute()
    }

    private fun emitCurrentRoute() {
        val route = window.location.pathname.trimStart('/')
        val params = parseParams(window.location.search)

        val routeInfo = RouteInfo(route, params)
        scope.launch {
            _routeChange.emit(routeInfo) // Emit the current route info to the Flow
        }
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