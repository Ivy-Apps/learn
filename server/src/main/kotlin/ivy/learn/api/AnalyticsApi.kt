package ivy.learn.api

import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.postEndpointAuthenticated
import ivy.learn.data.repository.AnalyticsRepository
import ivy.learn.domain.auth.AuthenticationService
import ivy.model.analytics.AnalyticsEventsRequest

class AnalyticsApi(
    private val analyticsRepository: AnalyticsRepository,
    private val authService: AuthenticationService,
) : Api {
    override fun Routing.endpoints() {
        postEvents()
    }

    private fun Routing.postEvents() {
        postEndpointAuthenticated<AnalyticsEventsRequest, Unit>(
            "analytics/events"
        ) { request, sessionToken ->
            val user = authService.getUser(sessionToken).bind()
            TODO()
        }
    }
}