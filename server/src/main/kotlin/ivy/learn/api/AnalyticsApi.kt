package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.postEndpointAuthenticated
import ivy.learn.domain.AnalyticsService
import ivy.learn.domain.auth.AuthenticationService
import ivy.model.analytics.LogAnalyticsEventsRequest

class AnalyticsApi(
    private val authService: AuthenticationService,
    private val analyticsService: AnalyticsService,
) : Api {
    override fun Routing.endpoints() {
        postEvents()
    }

    private fun Routing.postEvents() {
        postEndpointAuthenticated<LogAnalyticsEventsRequest, Unit>(
            "analytics/events"
        ) { request, sessionToken ->
            val user = authService.getUser(sessionToken).bind()
            analyticsService.logEvents(
                user = user,
                request = request
            ).bind()
        }
    }
}