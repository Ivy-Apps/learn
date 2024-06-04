package component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
    backButton: BackButton?,
    title: String,
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    topBarCenterContent: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LearnTopAppBar(
                backButton = backButton,
                title = title,
                centerContent = topBarCenterContent,
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
    modifier: Modifier = Modifier,
    actions: @Composable (Modifier) -> Unit = {},
    centerContent: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        content = {
            var backButtonWidth by remember { mutableStateOf(0.dp) }
            var actionsWidth by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current
            if (backButton != null) {
                BackButton(
                    modifier = Modifier.onSizeChanged {
                        backButtonWidth = with(density) { it.width.toDp() }
                    },
                    backButton = backButton
                )
            }
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.width(actionsWidth))

            if (centerContent != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(title)
                    Spacer(Modifier.height(4.dp))
                    centerContent()
                }
            } else {
                Text(title)
            }

            Spacer(Modifier.weight(1f))
            actions(
                Modifier.onSizeChanged {
                    actionsWidth = with(density) { it.width.toDp() }
                }
            )
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