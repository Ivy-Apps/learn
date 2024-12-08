package ivy.learn.api

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import ivy.data.source.AnalyticsDataSource
import ivy.di.Di
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.analytics.AnalyticsFixtures.eventDto
import ivy.learn.testsupport.analytics.AnalyticsFixtures.eventsRequest
import ivy.learn.testsupport.timeNow
import ivy.model.analytics.LogAnalyticsEventsRequest
import ivy.model.auth.SessionToken
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class AnalyticsServerTest : ServerTest() {
    enum class ValidEventsTestCase(
        val request: LogAnalyticsEventsRequest
    ) {
        SINGLE_EVENT(
            request = eventsRequest(
                eventDto(eventName = "intro__view")
            )
        ),
        MULTIPLE_EVENTS(
            request = eventsRequest(
                eventDto(eventName = "intro__view"),
                eventDto(eventName = "intro__click_continue"),
                eventDto(
                    eventName = "intro__click_continue",
                    time = timeNow().plus(30, DateTimeUnit.SECOND),
                ),
            )
        ),
        EVENT_WITH_0_IN_NAME(
            request = eventsRequest(
                eventDto(eventName = "lesson__chose_index0"),
            )
        ),
        EVENT_WITH_DIGIT_IN_NAME(
            request = eventsRequest(
                eventDto(eventName = "lesson__chose_index7"),
            )
        ),
        EVENT_WITH_PARAMS(
            request = eventsRequest(
                eventDto(
                    eventName = "lesson__click_continue",
                    params = mapOf(
                        "lesson" to "arrays",
                        "lessonItemId" to "intro"
                    ),
                )
            )
        ),
        VALID_MIXED_WITH_INVALID_EVENTS(
            request = eventsRequest(
                eventDto(eventName = "lesson__chose_index103"),
                eventDto(eventName = ""),
                eventDto(eventName = "OK$#@%"),
            )
        ),
    }

    @Test
    fun `logs valid events for single user`(
        @TestParameter testCase: ValidEventsTestCase
    ) = beTest {
        // Given
        val auth = registerUser("test@email.com")
        val datasource = Di.get<AnalyticsDataSource>()

        // When
        val response = datasource.logEvents(
            session = auth.session.token,
            request = testCase.request
        )

        // Then
        response.shouldBeRight()
    }

    @Test
    fun `logs valid events for multiple users`(
        @TestParameter testCase: ValidEventsTestCase
    ) = beTest {
        // Given
        val auths = (1..10).map { index ->
            registerUser("test_user$index$@email.com")
        }
        val datasource = Di.get<AnalyticsDataSource>()

        // When
        val responses = auths.map { auth ->
            datasource.logEvents(
                session = auth.session.token,
                request = testCase.request
            )
        }

        // Then
        responses.forEach { response ->
            response.shouldBeRight()
        }
    }

    enum class InvalidEventsTestCase(
        val request: LogAnalyticsEventsRequest
    ) {
        EMPTY_EVENTS(
            request = eventsRequest()
        ),
        BLANK_NAME_EVENT(
            request = eventsRequest(
                eventDto("")
            )
        ),
        INVALID_EVENT_NAME(
            request = eventsRequest(
                eventDto("INTRO_click_continue")
            )
        ),
        MULTIPLE_INVALID_EVENTS(
            request = eventsRequest(
                eventDto(eventName = ""),
                eventDto(eventName = "$#$#%"),
                eventDto(eventName = "INVALID"),
            )
        ),
    }

    @Test
    fun `does not log invalid events`(
        @TestParameter testCase: InvalidEventsTestCase
    ) = beTest {
        // Given
        val auth = registerUser("test@email.com")
        val datasource = Di.get<AnalyticsDataSource>()


        // When
        val response = datasource.logEvents(
            session = auth.session.token,
            request = testCase.request
        )

        // Then
        response.shouldBeLeft()
    }

    @Test
    fun `does not log events for invalid sessions`(
        @TestParameter testCase: InvalidEventsTestCase
    ) = beTest {
        // Given
        registerUser("test@test.com")
        val datasource = Di.get<AnalyticsDataSource>()

        // When
        val response = datasource.logEvents(
            session = SessionToken("invalid"),
            request = eventsRequest(
                eventDto("home__view")
            )
        )

        // Then
        response.shouldBeLeft()
    }

    @Test
    fun `does not log events for expired sessions`(
        @TestParameter testCase: InvalidEventsTestCase
    ) = beTest {
        // Given
        val auth = registerUser(
            email = "test@test.com",
            expiredSession = true,
        )
        val datasource = Di.get<AnalyticsDataSource>()

        // When
        val response = datasource.logEvents(
            session = auth.session.token,
            request = eventsRequest(
                eventDto("home__view")
            )
        )

        // Then
        response.shouldBeLeft()
    }
}