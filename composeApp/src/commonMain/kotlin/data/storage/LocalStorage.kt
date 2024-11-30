package data.storage

interface LocalStorage {
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): String?
    suspend fun putInt(key: String, value: Int)
    suspend fun getInt(key: String): Int?
    suspend fun putDouble(key: String, value: Double)
    suspend fun getDouble(key: String): Double?
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean?

    suspend fun remove(key: String)
    suspend fun removeAll()
    suspend fun keys(): List<String>
}

expect fun localStorage(): LocalStorage