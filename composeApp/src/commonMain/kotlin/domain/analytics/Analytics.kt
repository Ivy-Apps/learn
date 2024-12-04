package domain.analytics

import data.AnalyticsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.Logger

class Analytics(
    private val analyticsRepository: AnalyticsRepository,
    private val logger: Logger,
    private val appScope: CoroutineScope,
) {
    fun logEvent(eventName: String, params: Map<String, String>) {
        appScope.launch {
            logger.info("LOGGED: '$eventName' with ${Json.encodeToString(params)} params")

        }
    }
}