package navigation.redirects

interface Redirect {
    suspend fun handle(): Boolean
}