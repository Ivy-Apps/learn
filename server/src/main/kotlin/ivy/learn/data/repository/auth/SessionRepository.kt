package ivy.learn.data.repository.auth

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.SessionsTable
import ivy.learn.domain.model.Session
import ivy.learn.domain.model.UserId
import ivy.model.auth.SessionToken
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SessionRepository {
    fun findSessionByToken(token: SessionToken): Either<String, Session?> = catch({
        transaction {
            SessionsTable.selectAll()
                .where { SessionsTable.token eq token.value }
                .map(::rowToSession)
                .singleOrNull()
        }.right()
    }) { e ->
        Either.Left("Failed to find session by $token because $e")
    }

    private fun rowToSession(row: ResultRow): Session {
        return Session(
            token = SessionToken(row[SessionsTable.token]),
            userId = UserId(row[SessionsTable.userId].value),
            createdAt = row[SessionsTable.createdAt],
            expiresAt = row[SessionsTable.expiresAt],
        )
    }

    fun create(session: Session): Either<String, Session> = catch({
        transaction {
            SessionsTable.insert {
                it[userId] = session.userId.value
                it[token] = session.token.value
                it[createdAt] = session.createdAt
                it[expiresAt] = session.expiresAt
            }
        }
        Either.Right(session)
    }) { e ->
        Either.Left("Failed to insert session because $e")
    }

    fun delete(token: SessionToken): Either<String, Unit> = catch({
        transaction {
            SessionsTable.deleteWhere { SessionsTable.token eq token.value }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to delete session because $e")
    }
}