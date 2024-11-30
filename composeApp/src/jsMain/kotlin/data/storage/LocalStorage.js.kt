package data.storage

import kotlinx.browser.window

class WebLocalLocalStorage : LocalStorage {
    override suspend fun putString(
        key: String,
        value: String
    ) {
        storage.setItem(key, value)
    }

    override suspend fun getString(key: String): String? {
        return storage.getItem(key)
    }

    override suspend fun putInt(
        key: String,
        value: Int
    ) {
        storage.setItem(key, value.toString())
    }

    override suspend fun getInt(key: String): Int? {
        return storage.getItem(key)?.toIntOrNull()
    }


    override suspend fun putDouble(
        key: String,
        value: Double
    ) {
        storage.setItem(key, value.toString())
    }

    override suspend fun getDouble(key: String): Double? {
        return storage.getItem(key)?.toDoubleOrNull()
    }

    override suspend fun putBoolean(
        key: String,
        value: Boolean
    ) {
        storage.setItem(key, value.toString())
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return storage.getItem(key)?.toBooleanStrictOrNull()
    }

    override suspend fun remove(key: String) {
        storage.removeItem(key)
    }

    override suspend fun removeAll() {
        storage.clear()
    }

    override suspend fun keys(): List<String> {
        return (0..storage.length)
            .mapNotNull {
                storage.key(it)
            }
    }

    private val storage
        get() = window.localStorage
}

actual fun localStorage(): LocalStorage = WebLocalLocalStorage()