package domain.analytics

import data.AnalyticsRepository
import data.storage.LocalStorage
import domain.SessionManager
import ivy.model.analytics.AnalyticsEventDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import util.Logger
import util.TimeProvider

class Analytics(
  private val analyticsRepository: AnalyticsRepository,
  private val logger: Logger,
  private val appScope: CoroutineScope,
  private val timeProvider: TimeProvider,
  private val localStorage: LocalStorage,
  private val sessionManager: SessionManager,
  private val metrics: Metrics,
) {
  var enabled = true
    private set

  suspend fun initialize() {
    enabled = localStorage.getBoolean(KEY_ANALYTICS_ENABLED) ?: true
    logger.debug("$TAG Initialized as enabled = $enabled")
  }

  fun logEvent(
    source: Source,
    event: String,
    params: Map<String, String>? = null,
  ) {
    logEvent(
      eventName = "${source.value}__$event",
      params = params,
    )
  }

  private fun logEvent(
    eventName: String,
    params: Map<String, String>?,
  ) {
    if (!enabled) return

    appScope.launch {
      if (sessionManager.isLoggedIn()) {
        trackLoggedAnalyticsEvent(
          eventName = eventName,
          params = params,
        )
      } else {
        metrics.logMetric(
          name = eventName,
          params = params,
        )
      }
    }
  }

  private suspend fun trackLoggedAnalyticsEvent(
    eventName: String,
    params: Map<String, String>?,
  ) {
    logger.info("$TAG Tracking: '$eventName' with $params params.")
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

  suspend fun disable() {
    enabled = false
    localStorage.putBoolean(KEY_ANALYTICS_ENABLED, false)
    logger.info("$TAG Analytics disabled.")
  }

  suspend fun enable() {
    enabled = true
    localStorage.putBoolean(KEY_ANALYTICS_ENABLED, true)
    logger.info("$TAG Analytics enabled.")
  }

  companion object {
    const val TAG = "[Analytics]"
    const val KEY_ANALYTICS_ENABLED = "analytics.is_enabled"
  }
}