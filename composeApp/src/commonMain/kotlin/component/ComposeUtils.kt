package component

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun isLargeScreen(): Boolean {
    val density = LocalDensity.current
    val containerSize = LocalWindowInfo.current.containerSize
    return containerSize.width > with(density) { 600.dp.toPx() }
}

@Composable
fun platformHorizontalPadding(): Dp {
    return if (isLargeScreen()) 24.dp else 16.dp
}
