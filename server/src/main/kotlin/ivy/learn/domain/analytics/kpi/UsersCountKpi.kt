package ivy.learn.domain.analytics.kpi

import ivy.data.source.model.KpiDto
import ivy.learn.data.database.tables.UsersTable
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.transactions.transaction

class UsersCountKpi : Kpi {
  override suspend fun compute(): KpiDto = transaction {
    val count = UsersTable.select(UsersTable.id.count())
      .single()[UsersTable.id.count()]
    KpiDto(
      name = "# of users",
      value = count.toString()
    )
  }
}