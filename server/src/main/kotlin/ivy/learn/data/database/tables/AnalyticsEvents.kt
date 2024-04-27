package ivy.learn.data.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object AnalyticsEvents : UUIDTable() {
    val name = varchar("name", 50)
}
