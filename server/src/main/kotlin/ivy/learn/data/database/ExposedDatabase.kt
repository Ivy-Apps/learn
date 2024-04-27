package ivy.learn.data.database

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import org.jetbrains.exposed.sql.Database
import java.net.URI

data class ExposedDatabaseConfig(
    val url: String,
    val driver: String,
    val user: String,
    val password: String
)

class DbConfigProvider {
    /**
     * @param herokuDbUrl The Heroku "DATABASE_URL" environment variable.
     * In the format "postgres://$user:$password@$host:$port/$database".
     */
    fun fromHerokuDbUrl(
        herokuDbUrl: String?
    ): Either<HerokuConfigError, ExposedDatabaseConfig> = either {
        ensureNotNull(herokuDbUrl) { HerokuConfigError.NullUrl }
        ensure(herokuDbUrl.startsWith("postgres://")) {
            HerokuConfigError.InvalidUrl(herokuDbUrl, emptyList())
        }

        val parts = herokuDbUrl.replace("postgres://", "").split(":")
        // $user:$password@$host:$port/$database
        ensure(parts.size == 3) { HerokuConfigError.InvalidUrl(herokuDbUrl, parts) }
        val (user, passwordAndHost, portAndDatabase) = parts
        val (password, host) = passwordAndHost.split("@").also {
            ensure(it.size == 2) { HerokuConfigError.InvalidPasswordAndHost(passwordAndHost) }
        }
        val (port, database) = portAndDatabase.split("/").also {
            ensure(it.size == 2) { HerokuConfigError.InvalidPortAndDatabase(portAndDatabase) }
        }
        ensure(user.isNotBlank()) { HerokuConfigError.BlankUser }
        ensure(password.isNotBlank()) { HerokuConfigError.BlankPassword }
        ensure(host.isNotBlank()) { HerokuConfigError.BlankHost }
        ensure(port.isNotBlank() && port.all(Char::isDigit)) {
            HerokuConfigError.InvalidPort(port)
        }
        ensure(database.isNotBlank()) { HerokuConfigError.BlankDatabase }

        ExposedDatabaseConfig(
            url = "jdbc:postgresql://$host:$port/$database",
            driver = "org.postgresql.Driver",
            user = user,
            password = password
        )
    }

    sealed interface HerokuConfigError {
        data object NullUrl : HerokuConfigError
        data class InvalidUrl(
            val url: String,
            val parts: List<String>
        ) : HerokuConfigError

        data class InvalidPasswordAndHost(
            val passwordAndHost: String
        ) : HerokuConfigError

        data class InvalidPortAndDatabase(
            val portAndDatabase: String
        ) : HerokuConfigError

        data object BlankUser : HerokuConfigError
        data object BlankPassword : HerokuConfigError
        data object BlankHost : HerokuConfigError
        data class InvalidPort(val port: String) : HerokuConfigError
        data object BlankDatabase : HerokuConfigError
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