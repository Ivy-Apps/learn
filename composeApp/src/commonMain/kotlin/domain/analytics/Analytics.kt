package domain.analytics

import data.AnalyticsRepository
import ivy.model.analytics.AnalyticsEventDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.Logger
import util.TimeProvider

class Analytics(
    private val analyticsRepository: AnalyticsRepository,
    private val logger: Logger,
    private val appScope: CoroutineScope,
    private val timeProvider: TimeProvider,
) {
    fun logEvent(
        source: Source,
        event: String,
        params: Map<String, String> = emptyMap(),
    ) {
        logEvent(
            eventName = "${source.value}__$event",
            params = params,
        )
    }

    fun logEvent(
        eventName: String,
        params: Map<String, String> = emptyMap(),
    ) {
        appScope.launch {
            // TODO: Optimize later by batching events
            val paramsJson = Json.encodeToString(params)
            logger.info("$TAG Tracking: '$eventName' with $paramsJson params...")
            analyticsRepository.logEvent(
                setOf(
                    AnalyticsEventDto(
                        eventName = eventName,
                        time = timeProvider.timeNow(),
                        params = params,
                    )
                )
            ).onLeft { error ->
                logger.error("$TAG Error logging '$eventName': $error")
            }.onRight {
                logger.debug("$TAG Logged '$eventName'.")
            }
        }
    }

    companion object {
        const val TAG = "[Analytics]"
    }
}