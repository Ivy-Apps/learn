package ivy.learn.data.repository.auth

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.Sessions
import ivy.learn.domain.model.Session
import ivy.learn.domain.model.SessionToken
import ivy.learn.domain.model.UserId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SessionRepository {
    suspend fun findSessionByToken(token: SessionToken): Either<String, Session?> = catch({
        transaction {
            Sessions.selectAll()
                .where { Sessions.token eq token.value }
                .map(::rowToSession)
                .singleOrNull()
        }.right()
    }) { e ->
        Either.Left("Failed to find session by $token because $e")
    }

    private fun rowToSession(row: ResultRow): Session {
        return Session(
            token = SessionToken(row[Sessions.token]),
            userId = UserId(row[Sessions.userId].value),
            createdAt = row[Sessions.createdAt],
            expiresAt = row[Sessions.expiresAt],
        )
    }

    suspend fun create(session: Session): Either<String, Unit> = catch({
        transaction {
            Sessions.insert {
                it[userId] = session.userId.value
                it[token] = session.token.value
                it[createdAt] = session.createdAt
                it[expiresAt] = session.expiresAt
            }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to insert session because $e")
    }

    suspend fun delete(token: SessionToken): Either<String, Unit> = catch({
        transaction {
            Sessions.deleteWhere { Sessions.token eq token.value }
        }
        Either.Right(Unit)
    }) { e ->
        Either.Left("Failed to delete session because $e")
    }
}