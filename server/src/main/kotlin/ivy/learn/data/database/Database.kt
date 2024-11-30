package ivy.learn.data.database

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import ivy.learn.config.DatabaseConfig
import ivy.learn.data.database.tables.Analytics
import ivy.learn.data.database.tables.Sessions
import ivy.learn.data.database.tables.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class Database {
    fun init(config: DatabaseConfig): Either<String, Database> = catch({
        either {
            with(config) {
                Database.connect(
                    url = "jdbc:postgresql://$host:$port/$database",
                    driver = "org.postgresql.Driver",
                    user = user,
                    password = password
                ).let(::createDbSchema)
                    .mapLeft {
                        "Database schema creation failed: $it"
                    }.bind()
            }
        }
    }) { e ->
        Either.Left("Database connection failed: $e")
    }

    private fun createDbSchema(database: Database): Either<Throwable, Database> = catch({
        transaction {
            SchemaUtils.create(
                Users,
                Sessions,
                Analytics,
            )
        }
        Either.Right(database)
    }) {
        Either.Left(it)
    }
}