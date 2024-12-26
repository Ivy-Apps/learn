package ui.screen.kpi

import androidx.compose.runtime.Immutable
import ivy.data.source.model.KpiDto
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface KpiViewState {
  data object Loading : KpiViewState
  data object Error : KpiViewState
  data class Content(val kpis: ImmutableList<KpiDto>) : KpiViewState
}

sealed interface KpiViewEvent