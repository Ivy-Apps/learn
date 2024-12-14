import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import data.di.DataModule
import di.AppModule
import domain.di.DomainModule
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.SharedModule
import ivy.di.autowire.autoWire
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.ToastHost
import ui.theme.LearnTheme

@Composable
@Preview
fun App() {
    var initialized by mutableStateOf(false)
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        Di.init(
            SharedModule,
            AppModule,
            DataModule,
            DomainModule,
        )
        Di.appScope {
            register { uriHandler }
            autoWire(::AppViewModel)
        }
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
    val appViewModel = remember { Di.get<AppViewModel>() }
    appViewModel.Init()

    val navigation = remember { Di.get<Navigation>() }
    Box(modifier = Modifier.fillMaxSize()) {
        navigation.NavHost()
        ToastHost()
    }
}
