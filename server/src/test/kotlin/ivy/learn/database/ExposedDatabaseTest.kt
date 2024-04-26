package ivy.learn.database

import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.jetbrains.exposed.sql.Database
import org.junit.Test

class ExposedDatabaseTest {

    @Test
    fun `should connect to the database`() = mockkStatic(Database::class) {
        // given
        val host = "ec1-2-3.eu-west-1.compute.amazonaws.com"
        val port = 5432
        val user = "db_user"
        val password = "password"
        val dbUrl = "postgres://$password:$host:$port/$user"
        val exposedDatabase = ExposedDatabase(dbUrl)
        every {
            Database.connect(
                url = any(),
                driver = any(),
                user = any(),
                password = any()
            )
        } returns mockk()

        // when
        val result = exposedDatabase.connect()

        // then
        result.shouldBeRight()
    }

}