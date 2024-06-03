package ivy.content.lesson.programmingfundamentals

import ivy.content.LottieUrls
import ivy.model.TextStyle
import ivy.model.dsl.lessonContent
import ivy.model.dsl.printLessonJson
import ivy.model.dsl.textBuilder

fun programmingMathInDisguise() = lessonContent {
    lottie("harry_hi_anim") {
        jsonUrl = LottieUrls.HarrySunglases
    }
    text("harry_hi") {
        text = textBuilder {
            line("Meet Harry, a shape-shifting alien from the KX-147 planet.")
            line("Harry is a gifted scientist who can solve complex problems in a simple and elegant way.")
            line("He's also quite impatient and tends to test his ideas in Friday on production.")
        }
        style = TextStyle.Body
    }
    lottie("harry_crash_anim") {
        jsonUrl = LottieUrls.SpaceshipFly
    }
    text("harry_crash") {
        text = textBuilder {
            line("Unfortunately, during one of his time-traveling experiments... ")
            line("Harry crashed his spaceship on Earth. (Oops!)")
            line("Now he needs your help to fix it and get back home.")
            line("But don't worry, Harry has a plan!")
        }
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}