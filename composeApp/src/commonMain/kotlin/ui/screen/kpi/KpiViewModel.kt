package ui.screen.kpi

import androidx.compose.runtime.*
import arrow.core.Either
import data.KpisRepository
import ivy.data.source.model.KpisResponse
import kotlinx.collections.immutable.toImmutableList
import ui.ComposeViewModel

class KpiViewModel(
  private val repository: KpisRepository,
) : ComposeViewModel<KpiViewState, KpiViewEvent> {

  private var res by mutableStateOf<Either<String, KpisResponse>?>(null)

  @Composable
  override fun viewState(): KpiViewState {
    LaunchedEffect(Unit) {
      loadKpis()
    }

    return when (val result = res) {
      is Either.Left -> KpiViewState.Error(result.value)
      is Either.Right -> {
        val kpisResponse = result.value
        KpiViewState.Content(
          funnel = kpisResponse.funnel.toImmutableList(),
          kpis = kpisResponse.kpis.toImmutableList()
        )
      }
      null -> KpiViewState.Loading
    }
  }

  private suspend fun loadKpis() {
    res = null
    res = repository.fetchKpis()
  }

  override fun onEvent(event: KpiViewEvent) {
  }
}