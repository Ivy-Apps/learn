package ivy.learn.testsupport

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

fun timeNow(): Instant = Clock.System.now()