package ivy.learn.data.database.tables

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Analytics : UUIDTable(name = "analytics") {
    val userId = reference(
        name = "user_id",
        refColumn = Users.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
    ).index()
    val event = varchar("event", 320).index()
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
