package ui

import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

interface EventHandler<Event : Any, State : Any> {
    val eventTypes: Set<KClass<*>>
    suspend fun VmContext<State>.handleEvent(event: Event)
}

interface VmContext<State> {
    @EventHandlerDsl
    val state: State

    @EventHandlerDsl
    fun modify(transformation: (State) -> State)

    val screenScope: CoroutineScope
}

@DslMarker
annotation class EventHandlerDsl