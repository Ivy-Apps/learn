import kotlinx.coroutines.flow.Flow
import ui.navigation.Screen

interface SystemNavigation {
    val routeChange: Flow<RouteInfo>
    fun navigateTo(screen: Screen)
    fun navigateBack()
}

data class RouteInfo(
    val route: String,
    val params: Map<String, String>,
)

expect fun systemNavigation(): SystemNavigation