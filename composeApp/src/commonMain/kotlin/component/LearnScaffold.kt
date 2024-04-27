package component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    backButton: BackButton,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LearnTopAppBar(backButton = backButton)
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        content = content
    )
}


@Composable
private fun LearnTopAppBar(
    modifier: Modifier = Modifier,
    backButton: BackButton
) {
    TopAppBar(
        modifier = modifier,
        content = {
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
    )
}