package ui.screen.kpi

import androidx.compose.runtime.Immutable
import ivy.data.source.model.KpiDto
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface KpiViewState {
  data object Loading : KpiViewState
  data class Error(val errMsg: String) : KpiViewState
  data class Content(
    val funnel: ImmutableList<KpiDto>,
    val kpis: ImmutableList<KpiDto>
  ) : KpiViewState
}

sealed interface KpiViewEvent