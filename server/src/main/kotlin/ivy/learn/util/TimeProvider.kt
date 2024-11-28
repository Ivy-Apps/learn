package ivy.learn.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class TimeProvider {
    fun instantNow(): Instant = Clock.System.now()
}