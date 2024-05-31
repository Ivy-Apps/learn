package ivy.learn.testsupport

import ivy.data.LocalServerUrlProvider
import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.initDi
import kotlinx.coroutines.test.runTest

open class ApiTest {
    fun apiTest(block: suspend ApiTest.() -> Unit) = runTest {
        initDi(devMode = true)
        Di.appScope { register<ServerUrlProvider> { LocalServerUrlProvider() } }
        block()
        Di.reset()
    }
}