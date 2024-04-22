import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import di.AppModule
import ivy.di.Di
import ivy.di.SharedModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.ColorsDemo

@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        Di.init(
            modules = setOf(
                SharedModule,
                AppModule,
            )
        )
    }

    MaterialTheme {
        ColorsDemo()
    }
}