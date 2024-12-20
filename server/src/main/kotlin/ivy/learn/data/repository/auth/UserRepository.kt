package ivy.learn.data.repository.auth

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.UsersTable
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun findUserById(id: UserId): Either<String, User?> = catch({
        transaction {
            UsersTable.selectAll()
                .where { UsersTable.id eq id.value }
                .limit(1)
                .map(::rowToUser)
                .singleOrNull()
        }.right()
    }) { e ->
        Either.Left("Failed to find user by ID $id because $e")
    }

    // Find user by Email
    fun findUserByEmail(email: String): Either<String, User?> = catch({
        transaction {
            UsersTable.selectAll()
                .where { UsersTable.email eq email }
                .limit(1)
                .map(::rowToUser)
                .singleOrNull()
        }.right()
    }) { e ->
        Either.Left("Failed to find user by email $email because $e")
    }

    private fun rowToUser(row: ResultRow): User {
        return User(
            id = UserId(row[UsersTable.id].value),
            email = row[UsersTable.email],
            names = row[UsersTable.names],
            profilePicture = row[UsersTable.profilePictureUrl]
        )
    }

    fun create(user: User): Either<String, User> = catch({
        transaction {
            UsersTable.insert {
                it[id] = user.id.value
                it[email] = user.email
                it[names] = user.names
                it[profilePictureUrl] = user.profilePicture
            }
        }
        Either.Right(user)
    }) { e ->
        Either.Left("Failed to insert user $user because $e")
    }

    fun update(user: User): Either<String, Unit> = catch({
        transaction {
            val updatedRows = UsersTable.update(
                where = { UsersTable.id eq user.id.value }
            ) {
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

    fun delete(id: UserId): Either<String, Unit> = catch({
        transaction {
            val deletedRows = UsersTable.deleteWhere { UsersTable.id eq id.value }
            if (deletedRows != 1) {
                throw IllegalStateException("Unexpected number of users deleted! Deleted $deletedRows rows.")
            }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to delete user with $id because $e")
    }
}