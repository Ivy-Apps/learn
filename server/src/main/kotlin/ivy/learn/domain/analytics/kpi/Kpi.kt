package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto

interface Kpi {
  suspend fun compute(): KpiDto
}