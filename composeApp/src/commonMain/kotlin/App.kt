import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import di.AppDiModule
import ivy.di.SharedDiModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.ColorsDemo

@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        SharedDiModule.init()
        AppDiModule.init()
    }

    MaterialTheme {
        ColorsDemo()
    }
}