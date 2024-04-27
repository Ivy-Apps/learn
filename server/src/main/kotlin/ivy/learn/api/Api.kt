package ivy.learn.api

import io.ktor.server.routing.*

interface Api {
    fun Routing.endpoints()
}