package util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
    /**
     * Dispatcher for main thread operations.
     */
    val main: CoroutineDispatcher

    /**
     *  Dispatcher optimized for disk and network IO tasks.
     */
    val io: CoroutineDispatcher

    /**
     * Dispatcher optimized for CPU-intensive tasks.
     */
    val default: CoroutineDispatcher
}

class DispatchersProviderImpl : DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}

class FakeDispatchersProvider : DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val default: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
