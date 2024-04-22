import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
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
            val navigation = remember { Di.get<Navigation>() }
            LaunchedEffect(navigation) {
                // navigate to the initial screen
                navigation.navigate(HomeScreen())
            }
            navigation.NavHost()
        }
    }
}