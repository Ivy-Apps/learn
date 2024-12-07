package ivy.learn.data.database

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import ivy.learn.config.DatabaseConfig
import ivy.learn.data.database.tables.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class LearnDatabase {
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
                LessonsProgress,
                CompletedLessons,
            )
        }
        Either.Right(database)
    }) {
        Either.Left(it)
    }
}