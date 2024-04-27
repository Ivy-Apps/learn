package component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

data class BackButton(
    val icon: BackIcon = BackIcon.ArrowBack,
    val onBackClick: () -> Unit
)

enum class BackIcon {
    ArrowBack,
    Close
}

@Composable
fun LearnScaffold(
    modifier: Modifier = Modifier,
    backButton: BackButton?,
    title: String,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LearnTopAppBar(
                backButton = backButton,
                title = title
            )
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        content = content
    )
}

@Composable
private fun LearnTopAppBar(
    backButton: BackButton?,
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        content = {
            var backButtonWidth by remember { mutableStateOf(0.dp) }
            if (backButton != null) {
                val density = LocalDensity.current
                BackButton(
                    modifier = Modifier.onSizeChanged {
                        backButtonWidth = with(density) { it.width.toDp() }
                    },
                    backButton = backButton
                )
            }
            Spacer(Modifier.weight(1f))
            Text(title)
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.width(backButtonWidth))
        }
    )
}

@Composable
private fun BackButton(
    backButton: BackButton,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = backButton.onBackClick,
        content = {
            Icon(
                imageVector = when (backButton.icon) {
                    BackIcon.ArrowBack -> Icons.AutoMirrored.Filled.ArrowBack
                    BackIcon.Close -> Icons.Default.Close
                },
                contentDescription = null
            )
        }
    )
}