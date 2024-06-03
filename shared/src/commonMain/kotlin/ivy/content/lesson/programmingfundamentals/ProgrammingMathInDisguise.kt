package ivy.content.lesson.programmingfundamentals

import ivy.content.LottieUrls
import ivy.model.TextStyle
import ivy.model.dsl.lessonContent
import ivy.model.dsl.printLessonJson
import ivy.model.dsl.textBuilder

const val HarryPlanet = "KX-147"

fun programmingMathInDisguise() = lessonContent {
    text("meet_harry") {
        text = "Meet Harry!"
        style = TextStyle.Heading
    }
    lottie("harry_hi_anim") {
        jsonUrl = LottieUrls.HarrySunglases
    }
    text("harry_hi") {
        text = textBuilder {
            line("Harry is a shape-shifting alien from the $HarryPlanet planet.")
            line("Harry is a gifted scientist who can solve complex problems in a simple and elegant way.")
            line("He's also quite impatient and tends to test his ideas at Friday on production.")
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
    text("chapter_1_title") {
        text = "Chapter 1: Repairing the Spaceship"
        style = TextStyle.Heading
    }
    text("chater_1_intro") {
        text = textBuilder {
            line("Harry planned to use his superior math and science skills.")
            line("To repair his warp drive, Harry needs exactly 3141.59 grams of gold.")
            line("However, it turned out that things on Earth are different from his $HarryPlanet home planet...")
        }
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}