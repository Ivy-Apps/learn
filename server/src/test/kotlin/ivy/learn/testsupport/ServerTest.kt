package ivy.learn.testsupport

import arrow.core.getOrElse
import ivy.data.LocalServerUrlProvider
import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.config.ServerConfiguration
import ivy.learn.config.ServerConfigurationProvider
import ivy.learn.data.database.LearnDatabase
import ivy.learn.data.database.tables.Users
import ivy.learn.data.repository.auth.SessionRepository
import ivy.learn.data.repository.auth.UserRepository
import ivy.learn.domain.auth.Auth
import ivy.learn.domain.auth.AuthenticationService.Companion.SESSION_EXPIRATION_DAYS
import ivy.learn.domain.model.Session
import ivy.learn.domain.model.User
import ivy.learn.domain.model.UserId
import ivy.learn.initDi
import ivy.learn.util.Crypto
import ivy.learn.util.TimeProvider
import ivy.model.auth.SessionToken
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

open class ServerTest {
    fun beTest(block: suspend ServerTest.() -> Unit) = runTest {
        initDi(devMode = true)
        Di.appScope { register<ServerUrlProvider> { LocalServerUrlProvider() } }
        val serverConfig = Di.get<ServerConfigurationProvider>()
            .fromEnvironment()
            .getOrElse(::error)
        resetDb(serverConfig)
        block()
        Di.reset()
    }

    private fun resetDb(serverConfig: ServerConfiguration) {
        Di.get<LearnDatabase>().init(serverConfig.database)
        transaction {
            Users.deleteAll()
        }
    }

    protected fun registerUser(
        email: String = "test-user${timeNow()}@test.com",
        expiredSession: Boolean = false,
    ): Auth {
        return transaction {
            val user = Di.get<UserRepository>().create(
                user = User(
                    id = UserId(UUID.randomUUID()),
                    email = email,
                    names = null,
                    profilePicture = null
                )
            ).getOrElse(::error)
            val timeNow = Di.get<TimeProvider>().instantNow()
            val session = Di.get<SessionRepository>().create(
                session = Session(
                    token = SessionToken(Di.get<Crypto>().generateSecureToken()),
                    userId = user.id,
                    createdAt = timeNow,
                    expiresAt = if (expiredSession) {
                        timeNow.minus(SESSION_EXPIRATION_DAYS, DateTimeUnit.DAY, TimeZone.UTC)
                    } else {
                        timeNow.plus(SESSION_EXPIRATION_DAYS, DateTimeUnit.DAY, TimeZone.UTC)
                    },
                )
            ).getOrElse(::error)
            Auth(
                user = user,
                session = session
            )
        }
    }
}