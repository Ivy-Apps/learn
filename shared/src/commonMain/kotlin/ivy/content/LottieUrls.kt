package ivy.content

object LottieUrls {
    val HarrySunglasses = lottieJsonUrl("harry-hi-v1")
    val SpaceshipFly = lottieJsonUrl("spaceship-fly")
    val SpaceshipFixed = lottieJsonUrl("spaceship-fixed-v2")

    fun lottieJsonUrl(animationName: String): String =
        "https://raw.githubusercontent.com/ILIYANGERMANOV/ivy-resources/master/ivy-learn/lottie/$animationName.json"
}