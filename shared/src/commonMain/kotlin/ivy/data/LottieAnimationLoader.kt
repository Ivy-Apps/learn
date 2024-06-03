package ivy.data

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.catch
import arrow.core.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class LottieAnimationLoader(
    private val httpClient: HttpClient
) {
    suspend fun loadJson(url: String): Either<String, String> = catch({
        httpClient.get(url).body<String>().right()
    }) {
        "Failed to load Lottie animation from '$url'.".left()
    }
}