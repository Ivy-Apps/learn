package ivy.learn

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.learn.config.DatabaseConfig
import ivy.learn.config.GoogleOAuthConfig
import ivy.learn.config.ServerConfiguration
import ivy.learn.config.ServerConfigurationProvider
import ivy.learn.fake.FakeEnvironment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class ServerConfigurationTest {

    private val environment = FakeEnvironment()

    private lateinit var configurationProvider: ServerConfigurationProvider

    @Before
    fun setup() {
        environment.clear()
        configurationProvider = ServerConfigurationProvider(environment)
    }

    @Test
    fun `fromEnvironment should return a ServerConfiguration`() {
        // Given
        configureEnvironment()

        // When
        val res = configurationProvider.fromEnvironment()

        // Then
        res.shouldBeRight() shouldBe ServerConfiguration(
            database = DatabaseConfig(
                host = "localhost",
                database = "learn",
                user = "user",
                port = "5432",
                password = "password"
            ),
            privateContentGitHubPat = "pat",
            googleOAuth = GoogleOAuthConfig(
                clientId = "googleClientId",
                clientSecret = "googleClientSecret",
            )
        )
    }

    enum class MissingVariableTestCase(
        val missingVariable: String
    ) {
        HOST("IVY_LEARN_DB_HOST"),
        DATABASE("IVY_LEARN_DB_DATABASE"),
        USER("IVY_LEARN_DB_USER"),
        DB_PORT("IVY_LEARN_DB_PORT"),
        PASSWORD("IVY_LEARN_DB_PASSWORD"),
        GITHUB_PAT("IVY_LEARN_GITHUB_PAT"),
        GOOGLE_CLIENT_ID("IVY_GOOGLE_OAUTH_CLIENT_ID"),
        GOOGLE_CLIENT_SECRET("IVY_GOOGLE_OAUTH_CLIENT_SECRET"),
    }

    @Test
    fun `fromEnvironment should return an error when a variable is missing`(
        @TestParameter testCase: MissingVariableTestCase
    ) {
        // Given
        configureEnvironment()
        environment.removeVariable(testCase.missingVariable)

        // When
        val res = configurationProvider.fromEnvironment()

        // Then
        res.shouldBeLeft() shouldBe "Environment variable ${testCase.missingVariable} not found"
    }

    private fun configureEnvironment() {
        environment.setVariable("IVY_LEARN_DB_HOST", "localhost")
        environment.setVariable("IVY_LEARN_DB_DATABASE", "learn")
        environment.setVariable("IVY_LEARN_DB_USER", "user")
        environment.setVariable("IVY_LEARN_DB_PORT", "5432")
        environment.setVariable("IVY_LEARN_DB_PASSWORD", "password")
        environment.setVariable("IVY_LEARN_GITHUB_PAT", "pat")
        environment.setVariable("IVY_GOOGLE_OAUTH_CLIENT_ID", "googleClientId")
        environment.setVariable("IVY_GOOGLE_OAUTH_CLIENT_SECRET", "googleClientSecret")
    }
}