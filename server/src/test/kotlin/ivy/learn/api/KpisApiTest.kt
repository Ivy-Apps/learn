package ivy.learn.api

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.data.ServerUrlProvider
import ivy.data.source.KpisDataSource
import ivy.di.Di
import ivy.learn.testsupport.ServerTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class KpisApiTest : ServerTest() {

  enum class UnauthorizedUserTestCase(
    val email: String,
  ) {
    USER1(email = "user1"),
    HACKER(email = "iliyangermanov971@gmail.com")
  }

  @Test
  fun `fetch kpis - unauthorized user`(
    @TestParameter testCase: UnauthorizedUserTestCase
  ) = beTest {
    // Given
    val auth = registerUser(email = testCase.email)
    val datasource = Di.get<KpisDataSource>()

    // When
    val res = datasource.fetchKpis(auth.session.token)

    // Then
    res.shouldBeLeft()
  }

  @Test
  fun `fetch kpis - null session`() = beTest {
    // Given
    val httpClient = Di.get<HttpClient>()
    val urlProvider = Di.get<ServerUrlProvider>()

    // When
    val response = httpClient.get(
      "${urlProvider.serverUrl}/kpis"
    ) {
      contentType(ContentType.Application.Json)
    }

    // Then
    response.status.isSuccess() shouldBe false
  }

  enum class AuthorizedUserTestCase(
    val email: String,
  ) {
    ILIYAN(email = "iliyan.germanov971@gmail.com"),
    NICOLE(email = "nicolegeorgievva@gmail.com"),
  }

  @Test
  fun `fetch kpis - authorized user`(
    @TestParameter testCase: AuthorizedUserTestCase
  ) = beTest {
    // Given
    val auth = registerUser(email = testCase.email)
    val datasource = Di.get<KpisDataSource>()

    // When
    val res = datasource.fetchKpis(auth.session.token)

    // Then
    res.shouldBeRight().kpis.shouldNotBeEmpty()
  }
}