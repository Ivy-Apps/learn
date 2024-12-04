package domain.analytics

import data.AnalyticsRepository
import data.storage.LocalStorage
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
    private val localStorage: LocalStorage,
) {
    var enabled = true
        private set

    suspend fun initialize() {
        enabled = localStorage.getBoolean(ANALYTICS_ENABLED_KEY) ?: true
        logger.debug("$TAG Initialized as enabled = $enabled")
    }

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
        if (!enabled) return

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

    suspend fun disable() {
        enabled = false
        localStorage.putBoolean(ANALYTICS_ENABLED_KEY, false)
        logger.info("$TAG Analytics disabled.")
    }

    suspend fun enable() {
        enabled = true
        localStorage.putBoolean(ANALYTICS_ENABLED_KEY, true)
        logger.info("$TAG Analytics enabled.")
    }

    companion object {
        const val TAG = "[Analytics]"
        const val ANALYTICS_ENABLED_KEY = "analytics.is_enabled"
    }
}