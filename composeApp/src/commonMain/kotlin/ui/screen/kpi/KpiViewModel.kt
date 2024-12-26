package ui.screen.kpi

import androidx.compose.runtime.Composable
import ui.ComposeViewModel

class KpiViewModel : ComposeViewModel<KpiViewState, KpiViewEvent> {

  @Composable
  override fun viewState(): KpiViewState {
    TODO("Not yet implemented")
  }

  override fun onEvent(event: KpiViewEvent) {
  }
}