package ivy.content

object LottieUrls {
    val HarrySunglases = lottieJsonUrl("harry-hi-v1")
    val SpaceshipFly = lottieJsonUrl("spaceship-fly")

    fun lottieJsonUrl(animationName: String): String =
        "https://raw.githubusercontent.com/ILIYANGERMANOV/ivy-resources/master/ivy-learn/lottie/$animationName.json"
}