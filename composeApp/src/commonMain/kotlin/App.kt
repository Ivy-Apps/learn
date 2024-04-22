import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ivy.di.AppDiModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.ColorsDemo

@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        AppDiModule.init()
    }

    MaterialTheme {
        ColorsDemo()
    }
}