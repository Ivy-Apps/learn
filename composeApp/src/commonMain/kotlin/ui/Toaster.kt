package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.platformHorizontalPadding
import ivy.di.Di
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ui.theme.colorsExt

class Toaster(
    private val appScope: CoroutineScope,
) {
    private val _toasts = MutableSharedFlow<ToastViewState?>()
    val toasts: SharedFlow<ToastViewState?> = _toasts

    fun showToast(
        msg: String,
        durationMs: Int = 1_000,
    ) {
        appScope.launch {
            val toast = ToastViewState(
                msg = msg,
                durationMs = durationMs,
            )
            _toasts.emit(toast)
            delay(toast.durationMs.toLong())
            _toasts.emit(null)
        }
    }
}

@Composable
fun BoxScope.ToastHost() {
    val toaster = remember { Di.get<Toaster>() }
    val toast by toaster.toasts.collectAsState(initial = null)
    toast?.let {
        Toast(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(horizontal = platformHorizontalPadding())
                .padding(bottom = 24.dp),
            viewState = it
        )
    }
}

@Composable
fun Toast(
    viewState: ToastViewState,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(percent = 50)
            )
            .background(
                color = MaterialTheme.colorsExt.backgroundVariant,
                shape = RoundedCornerShape(percent = 50),
            )
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp,
            ),
        text = viewState.msg,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colorsExt.onBackgroundVariant,
    )
}

@Immutable
data class ToastViewState(
    val msg: String,
    val durationMs: Int,
)