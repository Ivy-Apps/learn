import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import di.AppModule
import ivy.di.Di
import ivy.di.SharedModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.Navigation
import ui.screen.home.HomeScreen

@Composable
@Preview
fun App() {
    var initialized by mutableStateOf(false)

    LaunchedEffect(Unit) {
        Di.init(
            modules = setOf(
                SharedModule,
                AppModule,
            )
        )
        initialized = true
    }

    MaterialTheme {
        if (initialized) {
            NavGraph()
        }
    }
}

@Composable
private fun NavGraph() {
    val navigation = remember { Di.get<Navigation>() }
    LaunchedEffect(navigation) {
        // navigate to the initial screen
        navigation.navigate(HomeScreen())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        navigation.NavHost()
    }
}