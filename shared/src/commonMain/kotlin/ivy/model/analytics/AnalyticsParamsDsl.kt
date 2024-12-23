package ivy.model.analytics

@AnalyticsParamsDsl
fun params(builder: AnalyticsParamsScope. () -> Unit): Map<String, String> {
  return AnalyticsParamScopeImpl().apply(builder).get()
}

class AnalyticsParamScopeImpl : AnalyticsParamsScope {
  private val paramsMap = mutableMapOf<String, String>()

  override fun param(key: String, value: String) {
    paramsMap[key] = value
  }

  fun get(): Map<String, String> = paramsMap
}

interface AnalyticsParamsScope {
  @AnalyticsParamsDsl
  fun param(key: String, value: String)
}

@DslMarker
annotation class AnalyticsParamsDsl