package ui.screen.kpi.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.LearnScaffold
import ivy.data.source.model.KpiDto
import ui.screen.kpi.KpiViewState

@Composable
fun KpiScreenContent(
  viewState: KpiViewState
) {
  LearnScaffold(
    backButton = null,
    title = "KPIs"
  ) { contentPadding ->
    Box(
      modifier = Modifier.padding(contentPadding)
        .fillMaxSize(),
      contentAlignment = Alignment.Center,
    ) {
      when (viewState) {
        is KpiViewState.Content -> Content(viewState)
        KpiViewState.Error -> ErrorState()
        KpiViewState.Loading -> LoadingState()
      }
    }
  }
}

@Composable
private fun Content(
  viewState: KpiViewState.Content,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(all = 24.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    items(items = viewState.kpis) { item ->
      KpiItem(item = item)
    }
  }
}

@Composable
private fun KpiItem(
  item: KpiDto,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier) {
    Text(
      text = item.name,
      style = MaterialTheme.typography.body1,
      fontWeight = FontWeight.SemiBold,
    )
    Spacer(Modifier.height(4.dp))
    Text(
      text = item.value,
      style = MaterialTheme.typography.body2
    )
  }
}

@Composable
private fun ErrorState(
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = "There was an error! Refresh the page"
  )
}

@Composable
private fun LoadingState(
  modifier: Modifier = Modifier,
) {
  CircularProgressIndicator(modifier = modifier.size(64.dp))
}