package ivy.learn.database

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import org.jetbrains.exposed.sql.Database
import java.net.URI

data class ExposedDatabaseConfig(
    val url: String,
    val driver: String,
    val user: String,
    val password: String
) {
    companion object {
        /**
         * @param herokuDbUrl The Heroku "DATABASE_URL" environment variable.
         * In the format "postgres://$password:$host:$port/$user".
         */
        fun Raise<HerokuConfigError>.fromHerokuDbUrl(herokuDbUrl: String?): ExposedDatabaseConfig {
            ensureNotNull(herokuDbUrl) { HerokuConfigError.NullUrl }

            val parts = herokuDbUrl.replace("postgres://", "")
                .split(":")
            ensure(parts.size == 3) { HerokuConfigError.InvalidUrl(herokuDbUrl, parts) }
            val (password, host, portAndUser) = parts
            val (port, username) = portAndUser.split("/").also {
                ensure(it.size == 2) { HerokuConfigError.InvalidPortAndUser(portAndUser) }
            }
            val dbUrl = TODO() //"jdbc:postgresql://${host}:${port}${dbUri.path.drop(1)}"

            return ExposedDatabaseConfig(
                url = dbUrl,
                driver = "org.postgresql.Driver",
                user = username,
                password = password
            )
        }
    }

    sealed interface HerokuConfigError {
        data object NullUrl : HerokuConfigError
        data class InvalidUrl(
            val url: String,
            val parts: List<String>
        ) : HerokuConfigError

        data class InvalidPortAndUser(
            val portAndUser: String
        ) : HerokuConfigError
    }
}

class ExposedDatabase(
    private val herokuDbUrl: String = System.getenv("DATABASE_URL")
) {
    fun connect(): Either<ConnectionError, Database> = catch({
        val dbUri = URI(herokuDbUrl)

        val username = dbUri.userInfo.split(":").first()
        val password = dbUri.userInfo.split(":").last()
        val dbUrl = "jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path.drop(1)}"

        Either.Right(
            Database.connect(
                url = dbUrl,
                driver = "org.postgresql.Driver",
                user = username,
                password = password
            )
        )
    }) { e ->
        Either.Left(ConnectionError.Unknown(e))
    }

    sealed interface ConnectionError {
        data class Unknown(val e: Throwable) : ConnectionError
    }
}