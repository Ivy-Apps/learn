package ivy.learn.api

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import ivy.data.source.MetricsDataSource
import ivy.di.Di
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.timeNow
import ivy.model.analytics.MetricDto
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(TestParameterInjector::class)
class MetricsApiTest : ServerTest() {

  enum class ValidMetricTestCase(
    val metric: MetricDto
  ) {
    METRIC_NO_PARAMS(
      metric = MetricDto(
        clientId = "web_${UUID.randomUUID()}",
        name = "intro__view",
        time = timeNow(),
        params = mapOf(),
      )
    ),
    METRIC_WITH_PARAMS(
      metric = MetricDto(
        clientId = "web_${UUID.randomUUID()}",
        name = "account__delete",
        time = timeNow(),
        params = mapOf(
          "reason" to "n/a",
        ),
      )
    ),
  }

  @Test
  fun `logs valid metrics`(
    @TestParameter testCase: ValidMetricTestCase
  ) = beTest {
    // Given
    val datasource = Di.get<MetricsDataSource>()

    // When
    val res = datasource.logMetric(testCase.metric)

    // Then
    res.shouldBeRight()
  }

  enum class InvalidMetricTestCase(
    val metric: MetricDto
  ) {
    BLANK_NAME(
      metric = MetricDto(
        clientId = "web_${UUID.randomUUID()}",
        name = "",
        time = timeNow(),
        params = mapOf(),
      )
    ),
    BLANK_CLIENT_ID(
      metric = MetricDto(
        clientId = "",
        name = "account__delete",
        time = timeNow(),
        params = mapOf(
          "reason" to "n/a",
        ),
      )
    ),
  }

  @Test
  fun `does not log invalid metrics`(
    @TestParameter testCase: InvalidMetricTestCase
  ) = beTest {
    // Given
    val datasource = Di.get<MetricsDataSource>()

    // When
    val res = datasource.logMetric(testCase.metric)

    // Then
    res.shouldBeLeft()
  }
}