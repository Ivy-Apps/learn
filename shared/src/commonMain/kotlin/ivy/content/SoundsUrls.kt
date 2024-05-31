package ivy.content

object SoundsUrls {
    val Ups = soundUrl("ups.wav")
    val Success = soundUrl("success.mp3")
    val Complete = soundUrl("complete.wav")

    fun soundUrl(fileName: String): String =
        "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/$fileName"
}