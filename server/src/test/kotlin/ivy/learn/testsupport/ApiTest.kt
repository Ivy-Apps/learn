package ivy.learn.testsupport

import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.initDi
import kotlinx.coroutines.test.runTest

open class ApiTest {
    fun apiTest(block: suspend ApiTest.() -> Unit) = runTest {
        initDi(devMode = true)
        Di.appScope { register<ServerUrlProvider> { FakeServerUrlProvider() } }
        block()
        Di.reset()
    }

    class FakeServerUrlProvider : ServerUrlProvider {
        override val serverUrl: String
            get() = "http://localhost:8081"
    }
}