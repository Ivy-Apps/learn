package ivy.learn.config

import arrow.core.Either
import arrow.core.raise.either

data class DatabaseConfig(
    val host: String,
    val database: String,
    val user: String,
    val port: String,
    val password: String
)

data class ServerConfiguration(
    val database: DatabaseConfig,
    val privateContentGitHubPat: String
)

class ServerConfigurationProvider(
    private val environment: Environment
) {
    fun fromEnvironment(): Either<String, ServerConfiguration> = either {
        ServerConfiguration(
            database = DatabaseConfig(
                host = environment.getVariable("IVY_LEARN_DB_HOST").bind(),
                database = environment.getVariable("IVY_LEARN_DB_DATABASE").bind(),
                user = environment.getVariable("IVY_LEARN_DB_USER").bind(),
                port = environment.getVariable("IVY_LEARN_DB_PORT").bind(),
                password = environment.getVariable("IVY_LEARN_DB_PASSWORD").bind()
            ),
            privateContentGitHubPat = environment.getVariable("IVY_LEARN_GITHUB_PAT").bind()
        )
    }
}