package ui

import kotlinx.coroutines.CoroutineScope

interface EventHandler<Event : Any, State : Any, Args : Any> {
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