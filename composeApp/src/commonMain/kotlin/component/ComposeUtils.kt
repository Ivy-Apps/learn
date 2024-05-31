package component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun isLargeScreen(): Boolean = screenWidth() > 600.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun screenWidth(): Dp {
    val density = LocalDensity.current
    val containerSize = LocalWindowInfo.current.containerSize
    return with(density) { containerSize.width.toDp() }
}

@Composable
fun screenType(): ScreenType = when {
    screenWidth() < 600.dp -> ScreenType.Mobile
    screenWidth() < 1200.dp -> ScreenType.Tablet
    else -> ScreenType.Desktop
}

@Immutable
enum class ScreenType {
    Mobile,
    Tablet,
    Desktop
}

@Composable
fun platformHorizontalPadding(): Dp {
    return if (isLargeScreen()) 24.dp else 16.dp
}
