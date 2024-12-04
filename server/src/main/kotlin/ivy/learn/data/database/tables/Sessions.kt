package ivy.learn.data.database.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Sessions : Table() {
    val token = varchar("token", length = 128).uniqueIndex()
    val userId = reference(
        name = "user_id",
        refColumn = Users.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
    ).index()
    val createdAt = timestamp("created_at").defaultExpression(CurrentTimestamp)
    val expiresAt = timestamp("expires_at¬")

    override val primaryKey = PrimaryKey(token)
}