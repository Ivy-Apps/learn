package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import ivy.di.Di
import ivy.di.Di.register
import kotlinx.coroutines.*
import ui.ComposeViewModel

@Immutable
abstract class Screen<ViewState : Any, ViewEvent : Any> {

  abstract val name: String
  abstract fun toRoute(): Route
  protected abstract fun Di.Scope.onDi()
  protected abstract fun getViewModel(affinity: Di.Scope): ComposeViewModel<ViewState, ViewEvent>

  private lateinit var job: CompletableJob
  protected lateinit var screenScope: CoroutineScope
  private val diScope: Lazy<Di.Scope> = lazy { Di.newScope(name) }

  private val viewModel by lazy { getViewModel(diScope.value) }

  private var initialized = false

  fun initialize() {
    if (initialized) return

    job = SupervisorJob()
    screenScope = CoroutineScope(Dispatchers.Main + job + CoroutineName(name))
    val diScope = diScope.value
    Di.inScope(diScope) {
      register { screenScope }
      onDi()
    }
    initialized = true
  }

  fun destroy() {
    job.cancel()
    Di.clear(diScope.value)
    initialized = false
  }

  @Composable
  fun Content() {
    Content(
      viewState = viewModel.viewState(),
      onEvent = viewModel::onEvent,
    )
  }

  @Composable
  protected abstract fun Content(
    viewState: ViewState,
    onEvent: (ViewEvent) -> Unit
  )
}