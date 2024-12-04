package util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class TimeProvider {
    fun timeNow(): Instant = Clock.System.now()
}