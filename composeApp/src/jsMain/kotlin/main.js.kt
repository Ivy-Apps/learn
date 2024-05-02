import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import ivy.di.Di
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import ui.navigation.Navigation

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeTarget") {
            App()
        }
    }
    setupBackNavigationHandler()
}

fun setupBackNavigationHandler() {
    window.addEventListener("popstate", {
        val navigation = Di.get<Navigation>()
        if (navigation.backstack().size > 1) {
            navigation.back()
        }
    })
}