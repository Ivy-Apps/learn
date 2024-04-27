package ivy.learn.database

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.learn.data.database.DbConfigProvider
import ivy.learn.data.database.DbConfigProvider.HerokuConfigError
import ivy.learn.data.database.ExposedDatabaseConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class DbConfigProviderTest {

    private lateinit var configProvider: DbConfigProvider

    @Before
    fun setup() {
        configProvider = DbConfigProvider()
    }

    @Test
    fun `valid Heroku DB URL should return ExposedDatabaseConfig`() {
        // given
        val validUrl = "postgres://user:password@ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db"

        // when
        val res = configProvider.fromHerokuDbUrl(validUrl)

        // then
        res.shouldBeRight() shouldBe ExposedDatabaseConfig(
            url = "jdbc:postgresql://ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db",
            driver = "org.postgresql.Driver",
            user = "user",
            password = "password"
        )
    }

    enum class InvalidDbUrlTestCase(
        val dbUrl: String?,
        val expectedError: HerokuConfigError
    ) {
        NullUrl(dbUrl = null, expectedError = HerokuConfigError.NullUrl),
        InvalidUrl(
            dbUrl = "postgres://user:password",
            expectedError = HerokuConfigError.InvalidUrl(
                "postgres://user:password", listOf("user", "password")
            )
        ),
        InvalidUrl2(
            dbUrl = "jdbc://user:password@ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db",
            expectedError = HerokuConfigError.InvalidUrl(
                "jdbc://user:password@ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db", emptyList()
            )
        ),
        MissingUser(
            dbUrl = "postgres://:password@ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db",
            expectedError = HerokuConfigError.BlankUser
        ),
        MissingPassword(
            dbUrl = "postgres://user:@ec1-2-3.eu-west-1.compute.amazonaws.com:1234/db",
            expectedError = HerokuConfigError.BlankPassword
        ),
        MissingHost(
            dbUrl = "postgres://user:pass@:1234/db",
            expectedError = HerokuConfigError.BlankHost
        ),
        EmptyPort(
            dbUrl = "postgres://user:pass@host:/db",
            expectedError = HerokuConfigError.InvalidPort("")
        ),
        InvalidPort(
            dbUrl = "postgres://user:pass@host:abc/db",
            expectedError = HerokuConfigError.InvalidPort("abc")
        ),
        MissingDatabase(
            dbUrl = "postgres://user:pass@host:123/",
            expectedError = HerokuConfigError.BlankDatabase
        ),
    }

    @Test
    fun `invalid Heroku DB URL should return error`(
        @TestParameter testCase: InvalidDbUrlTestCase
    ) {
        // given
        val dbUrl = testCase.dbUrl

        // when
        val res = configProvider.fromHerokuDbUrl(dbUrl)

        // then
        res.shouldBeLeft() shouldBe testCase.expectedError
    }
}