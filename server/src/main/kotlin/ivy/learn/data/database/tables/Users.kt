package ivy.learn.data.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object Users : UUIDTable(name = "users") {
    val email = varchar("email", length = 320).uniqueIndex()
    val names = varchar("names", length = 320).nullable()
    val profilePictureUrl = varchar("profile_picture_url", length = 2048).nullable()
}