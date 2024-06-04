package ivy.content.lesson.programmingfundamentals

import ivy.content.LottieUrls
import ivy.model.LessonItemId
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
    text("harry_experimenting") {
        text = "Harry loves experimenting... a lot!"
        style = TextStyle.Heading
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
        text = "Chapter 1: Repairing the spaceship"
        style = TextStyle.Heading
    }
    image("harry_plan_mg") {
        imageUrl = "https://i.ibb.co/gvBWYJ3/harry-spaceship-repair.webp"
    }
    text("chater_1_intro") {
        text = textBuilder {
            line("Harry planned to use his superior math and science skills.")
            line("To repair his warp drive, Harry needs exactly pure gold that weights 3141.59 grams.")
            line("However, it turned out that things on Earth are different from his $HarryPlanet home planet...")
        }
        style = TextStyle.BodyMediumSpacing
    }
    choice("gravity_choice") {
        question = "Do you think 3141.59 grams of gold on $HarryPlanet is the same as on Earth?"
        option(
            text = "Same weight, gold is gold",
            next = LessonItemId("same_w1"),
        )
        option(
            text = "Different weight, gravity affects weight",
            next = LessonItemId("diff_w1"),
        )
    }
    text("same_w1", next = "same_w2") {
        text = "Harry says \"Nope\""
        style = TextStyle.Heading
    }
    text("same_w2", next = "gravity_question") {
        text = textBuilder {
            line("That's very unlikely! Gravity affects weights.")
            line("weight = mass * gravitational acceleration")
            line("Assuming the Earth's and my planet's gravity are different, then the gold will weight differently.")
        }
        style = TextStyle.Body
    }
    text("diff_w1", next = "diff_w2") {
        text = "Harry says \"That's right!\""
        style = TextStyle.Heading
    }
    text("diff_w2", next = "gravity_question") {
        text = textBuilder {
            line("Correct! Gravity affects weights.")
            line("weight = mass * gravitational acceleration")
            line("Assuming the Earth's and my planet's gravity are different, then the gold will weight differently.")
        }
        style = TextStyle.Body
    }
    question("gravity_question") {
        question = "What is the formula for weight?"
        answer(
            text = "weight = mass * gravitational acceleration",
            correct = true,
            explanation = "The higher the gravity, the more you weight."
        )
        answer(
            text = "weight = mass / gravitational acceleration",
            explanation = "Gravity pulls you down, not up."
        )
        answer(
            text = "weight = mass + gravitational acceleration",
            explanation = "Gravity is not added to your mass."
        )
        answer(
            text = "weight = mass - gravitational acceleration",
            explanation = "Gravity is not subtracted from your mass."
        )
    }
    text("first_program") {
        text = textBuilder {
            line("Knowing that let's create our first program!")
            line("We need a function that will calculate the mass of gold by its weight on Earth.")
        }
        style = TextStyle.BodyBigSpacing
    }
    text("final") {
        text = "End of lesson."
        style = TextStyle.Heading
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}