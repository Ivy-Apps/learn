package ivy.learn.api

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeRight
import ivy.data.source.AnalyticsDataSource
import ivy.di.Di
import ivy.learn.testsupport.ApiTest
import ivy.learn.testsupport.analytics.AnalyticsFixtures
import ivy.model.analytics.AnalyticsEventDto
import ivy.model.analytics.LogAnalyticsEventsRequest
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class AnalyticsApiTest : ApiTest() {
    enum class ValidEventsTestCase(
        val request: LogAnalyticsEventsRequest
    ) {
        SINGLE_EVENT(
            request = AnalyticsFixtures.eventsRequest(
                AnalyticsEventDto(
                    eventName = "intro__view",
                    time = Clock.System.now(),
                    params = emptyMap(),
                )
            )
        ),
        MULTIPLE_EVENTS(
            request = AnalyticsFixtures.eventsRequest(
                AnalyticsEventDto(
                    eventName = "intro__view",
                    time = Clock.System.now(),
                    params = emptyMap(),
                ),
                AnalyticsEventDto(
                    eventName = "intro__click_continue",
                    time = Clock.System.now().plus(30, DateTimeUnit.SECOND),
                    params = emptyMap(),
                ),
            )
        ),
        EVENT_WITH_PARAMS(
            request = AnalyticsFixtures.eventsRequest(
                AnalyticsEventDto(
                    eventName = "lesson__click_continue",
                    time = Clock.System.now(),
                    params = mapOf(
                        "lesson" to "arrays",
                        "lessonItemId" to "intro"
                    ),
                )
            )
        ),
    }

    @Test
    fun `logs valid events`(
        @TestParameter testCase: ValidEventsTestCase
    ) = apiTest {
        // Given
        val auth = registerUser("test@email.com")
        val datasource = Di.get<AnalyticsDataSource>()


        // When
        val response = datasource.logEvents(
            sessionToken = auth.session.token,
            request = testCase.request
        )

        // Then
        response.shouldBeRight()
    }
}