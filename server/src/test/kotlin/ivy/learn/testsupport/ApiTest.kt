package ivy.learn.testsupport

import io.ktor.server.testing.*
import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.LearnServer
import ivy.learn.initDi

open class ApiTest {
    fun apiTest(block: suspend ApiTest.() -> Unit) = testApplication {

        initDi(devMode = true)
        Di.appScope { register<ServerUrlProvider> { FakeServerUrlProvider() } }
        application {
            Di.get<LearnServer>().init(this).onLeft {
                throw Exception("Server initialization failed: $it")
            }
        }
        block()
    }

    class FakeServerUrlProvider : ServerUrlProvider {
        override val serverUrl: String
            get() = "http://localhost:8081"
    }
}