package ivy.learn.data.database.tables

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object MetricsTable : UUIDTable(name = "metrics") {
  val clientId = varchar("client_id", length = 64).index()
  val name = varchar("name", 320).index()
  val time = timestamp("time")
  val params = json<Map<String, String>>(
    name = "params",
    serialize = { value ->
      Json.encodeToString(value)
    },
    deserialize = { json ->
      Json.decodeFromString(json)
    }
  ).nullable()
}
