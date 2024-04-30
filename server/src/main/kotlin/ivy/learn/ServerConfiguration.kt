package ivy.learn

data class DatabaseConfig(
    val host: String,
    val database: String,
    val user: String,
    val port: String,
    val password: String
)

data class ServerConfiguration(
    val dbConfig: DatabaseConfig,
    val privateContentGitHubPat: String
)

class Environment {
    fun getVariable(name: String): String = System.getenv(name)
        ?: throw InitializationError("Environment variable $name not found")
}

class ServerConfigurationProvider(
    private val environment: Environment
) {
    fun fromEnvironment(): ServerConfiguration = ServerConfiguration(
        dbConfig = DatabaseConfig(
            host = environment.getVariable("IVY_LEARN_DB_HOST"),
            database = environment.getVariable("IVY_LEARN_DB_DATABASE"),
            user = environment.getVariable("IVY_LEARN_DB_USER"),
            port = environment.getVariable("IVY_LEARN_DB_PORT"),
            password = environment.getVariable("IVY_LEARN_DB_PASSWORD")
        ),
        privateContentGitHubPat = environment.getVariable("IVY_LEARN_GITHUB_PAT")
    )
}