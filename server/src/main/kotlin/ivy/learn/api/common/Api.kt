package ivy.learn.api.common

import io.ktor.server.routing.*

interface Api {
    fun Routing.endpoints()
}