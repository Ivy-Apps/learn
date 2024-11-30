package data.storage

import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.get
import org.w3c.dom.set

class WebLocalLocalStorage : LocalStorage {
    override suspend fun putString(
        key: String,
        value: String
    ) = withContext(Dispatchers.Default) {
        window.localStorage[key] = value
    }

    override suspend fun getString(key: String): String? = withContext(Dispatchers.Default) {
        window.localStorage[key]
    }

    override suspend fun putInt(
        key: String,
        value: Int
    ) = withContext(Dispatchers.Default) {
        window.localStorage[key] = value.toString()
    }

    override suspend fun getInt(key: String): Int? = withContext(Dispatchers.Default) {
        window.localStorage[key]?.toIntOrNull()
    }

    override suspend fun putDouble(
        key: String,
        value: Double
    ) = withContext(Dispatchers.Default) {
        window.localStorage[key] = value.toString()
    }

    override suspend fun getDouble(key: String): Double? = withContext(Dispatchers.Default) {
        window.localStorage[key]?.toDoubleOrNull()
    }

    override suspend fun putBoolean(
        key: String,
        value: Boolean
    ) = withContext(Dispatchers.Default) {
        window.localStorage[key] = value.toString()
    }

    override suspend fun getBoolean(key: String): Boolean? = withContext(Dispatchers.Default) {
        window.localStorage[key]?.toBooleanStrictOrNull()
    }

    override suspend fun remove(key: String) {
        window.localStorage.removeItem(key)
    }

    override suspend fun removeAll() {
        window.localStorage.clear()
    }

    override suspend fun keys(): List<String> {
        return (0..window.localStorage.length)
            .mapNotNull {
                window.localStorage.key(it)
            }
    }
}

actual fun localStorage(): LocalStorage = WebLocalLocalStorage()