import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import di.AppModule
import ivy.di.Di
import ivy.di.SharedModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.Navigation
import ui.screen.intro.IntroScreen
import ui.theme.LearnTheme

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

    LearnTheme {
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
        navigation.navigate(IntroScreen())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        navigation.NavHost()
    }
}