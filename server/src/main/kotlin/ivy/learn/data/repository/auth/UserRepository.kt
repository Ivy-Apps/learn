package ivy.learn.data.repository.auth

import arrow.core.Either
import arrow.core.raise.catch
import ivy.learn.data.database.tables.Users
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository {
    suspend fun create(user: User): Either<String, Unit> = catch({
        transaction {
            Users.insert {
                it[id] = user.id.value
                it[email] = user.email
                it[names] = user.names
                it[profilePictureUrl] = user.profilePicture
            }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to insert user $user because $e")
    }

    suspend fun update(user: User): Either<String, Unit> = catch({
        transaction {
            val updatedRows = Users.update({ Users.id eq user.id.value }) {
                it[id] = user.id.value
                it[email] = user.email
                it[names] = user.names
                it[profilePictureUrl] = user.profilePicture
            }
            if (updatedRows != 1) {
                throw IllegalStateException("Unexpected number of users updated! Updated $updatedRows rows.")
            }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to update user $user because $e")
    }

    suspend fun delete(id: UserId): Either<String, Unit> = catch({
        transaction {
            val deletedRows = Users.deleteWhere { Users.id eq id.value }
            if (deletedRows != 1) {
                throw IllegalStateException("Unexpected number of users deleted! Deleted $deletedRows rows.")
            }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to delete user with $id because $e")
    }
}