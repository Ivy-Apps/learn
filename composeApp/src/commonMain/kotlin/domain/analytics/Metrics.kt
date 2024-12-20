package domain.analytics

import Platform
import data.MetricsRepository
import data.storage.LocalStorage
import ivy.model.analytics.MetricDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import util.Logger
import util.TimeProvider
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Metrics(
  private val metricsRepository: MetricsRepository,
  private val logger: Logger,
  private val appScope: CoroutineScope,
  private val timeProvider: TimeProvider,
  private val localStorage: LocalStorage,
  private val platform: Platform,
) {
  private var clientId: String? = null

  fun logMetric(
    name: String,
    params: Map<String, String>? = null,
  ) {
    appScope.launch {
      val clientId = ensureClientId()
      val metric = MetricDto(
        clientId = clientId,
        name = name,
        time = timeProvider.timeNow(),
        params = params,
      )
      metricsRepository.log(
        metric = metric
      ).onRight {
        logger.debug("$TAG Logged '${metric.name}' :$metric.")
      }.onLeft { errMsg ->
        logger.error("$TAG Failed to '${metric.name}': $metric because $errMsg.")
      }
    }
  }

  private suspend fun ensureClientId(): String {
    return clientId // in-memory client id
      ?: cachedClientId()
      ?: registerNewClientId()
  }

  private suspend fun cachedClientId(): String? =
    localStorage.getString(KEY_METRICS_CLIENT_ID)
      ?.also { clientId = it }

  private fun registerNewClientId(): String = generateClientId().also { newClientId ->
    clientId = newClientId
    appScope.launch {
      localStorage.putString(KEY_METRICS_CLIENT_ID, newClientId)
    }
  }

  @OptIn(ExperimentalUuidApi::class)
  private fun generateClientId(): String = "${platform.name}_${Uuid.random().toHexString()}"

  companion object {
    const val TAG = "[Metrics]"
    const val KEY_METRICS_CLIENT_ID = "metrics.client_id"
  }
}