package ui

import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

interface EventHandler<Event : Any, State : Any, Args : Any> {
    val eventTypes: Set<KClass<*>>
    suspend fun VmContext<State, Args>.handleEvent(event: Event)
}

interface VmContext<State : Any, Args : Any> {
    @EventHandlerDsl
    val state: State

    @EventHandlerDsl
    fun modifyState(transformation: (State) -> State)

    val screenScope: CoroutineScope

    val args: Args
}

@DslMarker
annotation class EventHandlerDsl