package util

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.jvm.JvmInline

@JvmInline
value class AppScope internal constructor(val get: CoroutineScope)

fun initAppScope(): AppScope {
  return AppScope(CoroutineScope(Dispatchers.Main + CoroutineName("App")))
}