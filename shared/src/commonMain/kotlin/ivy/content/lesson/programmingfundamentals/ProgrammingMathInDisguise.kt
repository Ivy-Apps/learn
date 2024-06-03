package ivy.learn.data.cms.lesson.programmingfundamentals

import ivy.model.TextStyle
import ivy.model.dsl.lessonContent
import ivy.model.dsl.printLessonJson

fun programmingMathInDisguise() = lessonContent {
    lottie("harry_hi_anim") {
        jsonUrl = "tbd"
    }
    text("harry_hi") {
        text = "Meet Harry, a shape-shifting alien from the KX-147 planet. " +
                "Harry is a gifted scientist who can solve complex problems in a simple and elegant way. " +
                "He's also quite impatient and tends to test his ideas in Friday on production."
        style = TextStyle.Body
    }
    lottie("harry_crash_anim") {
        jsonUrl = "tbf"
    }
    text("harry_crash") {
        text = "Unfortunately, during one of his time-traveling experiments, Harry crashed his spaceship on Earth. " +
                "He needs your help to fix it and get back home. " +
                "Harry has a plan, but he needs your knowledge and help to fix his ship."
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}