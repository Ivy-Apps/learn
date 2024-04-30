package ivy.learn.data.database

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import ivy.learn.DatabaseConfig
import ivy.learn.data.database.tables.AnalyticsEvents
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class Database {
    fun init(config: DatabaseConfig): Either<InitializationError, Database> = catch({
        either {
            with(config) {
                Database.connect(
                    url = "jdbc:postgresql://$host:$port/$database",
                    driver = "org.postgresql.Driver",
                    user = user,
                    password = password
                ).let(::createDbSchema)
                    .mapLeft(InitializationError::DbSchemaError).bind()
            }
        }
    }) { e ->
        Either.Left(InitializationError.Unknown(e))
    }

    private fun createDbSchema(database: Database): Either<Throwable, Database> = catch({
        transaction {
            SchemaUtils.create(AnalyticsEvents)
        }
        Either.Right(database)
    }) {
        Either.Left(it)
    }

    sealed interface InitializationError {
        data class DbSchemaError(val error: Throwable) : InitializationError
        data class Unknown(val e: Throwable) : InitializationError
    }
}