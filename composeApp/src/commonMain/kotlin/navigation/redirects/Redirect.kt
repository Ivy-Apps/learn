package navigation.redirects

interface Redirect {
    val name: String
    suspend fun handle(): Boolean
}