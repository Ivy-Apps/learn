package ivy.learn

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ivy.di.Di
import ui.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    override fun onBackPressed() {
        val navigation = Di.get<Navigation>()
        if (navigation.backstack().size > 1) {
            navigation.back()
        } else {
            super.onBackPressed()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}