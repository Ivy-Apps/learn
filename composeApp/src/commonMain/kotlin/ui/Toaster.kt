package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.platformHorizontalPadding
import ivy.di.Di
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ui.theme.IvyTheme
import ui.theme.colorsExt
import util.AppScope

class Toaster(
    private val appScope: AppScope,
) {
    private val _toasts = MutableSharedFlow<ImmutableList<ToastViewState>>()
    val toasts: SharedFlow<ImmutableList<ToastViewState>> = _toasts

    private val toastQueue = mutableListOf<ToastViewState>()

    fun showToast(
        msg: String,
        durationMs: Int = 2_000,
    ) {
        appScope.get.launch {
            val toast = ToastViewState(
                msg = msg,
                durationMs = durationMs,
            )
            // Add the toast to the queue
            toastQueue.add(toast)

            // Emit the updated queue
            _toasts.emit(toastQueue.toImmutableList())

            // Wait for the toast's duration
            delay(toast.durationMs.toLong())

            // Remove the toast from the queue after the duration
            toastQueue.remove(toast)

            // Emit the updated queue
            _toasts.emit(toastQueue.toImmutableList())
        }
    }
}

@Composable
fun BoxScope.ToastHost() {
    val toaster = remember { Di.get<Toaster>() }
    val toasts by toaster.toasts.collectAsState(initial = persistentListOf())
    val toastsByOrder = remember(toasts) { toasts.reversed().toImmutableList() }
    Column(
        modifier = Modifier.align(Alignment.BottomCenter)
            .padding(horizontal = platformHorizontalPadding())
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for ((i, toast) in toastsByOrder.withIndex()) {
            if (i > 0) {
                Spacer(Modifier.height(8.dp))
            }
            Toast(
                viewState = toast,
            )
        }
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
                color = MaterialTheme.colorsExt.backgroundVariant,
                shape = RoundedCornerShape(percent = 50)
            )
            .background(
                color = MaterialTheme.colorsExt.onBackgroundVariant,
                shape = RoundedCornerShape(percent = 50),
            )
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp,
            ),
        text = viewState.msg,
        style = IvyTheme.typography.b2,
        color = MaterialTheme.colorsExt.backgroundVariant,
    )
}

@Immutable
data class ToastViewState(
    val msg: String,
    val durationMs: Int,
)