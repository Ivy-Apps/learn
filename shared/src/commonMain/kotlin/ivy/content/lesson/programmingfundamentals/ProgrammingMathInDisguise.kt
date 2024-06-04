package ivy.content.lesson.programmingfundamentals

import ivy.content.LottieUrls
import ivy.model.TextStyle
import ivy.model.dsl.*

const val HarryPlanet = "KX-147"
const val CoreTemp = 314

fun programmingMathInDisguise() = lessonContent {
    meetHarry()
    harryCrash()
    helpHarry()
    theCore()
    questionCoreTempKelvin()
    questionCoreTempHaskell()
}

private fun LessonContentScope.meetHarry() {
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
}

private fun LessonContentScope.harryCrash() {
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
}

private fun LessonContentScope.helpHarry() {
    text("chapter_1_title") {
        text = "Chapter 1: Repairing the spaceship"
        style = TextStyle.Heading
    }
    image("harry_plan_img") {
        imageUrl = "https://i.ibb.co/gvBWYJ3/harry-spaceship-repair.webp"
    }
    text("chater_1_intro") {
        text = textBuilder {
            line("Harry's plan is to use his superior math and science skills.")
            line("However, he's not very familiar with the Earth and its technologies.")
            line("Harry only knows math and a little bit of Haskell.")
        }
        style = TextStyle.BodyMediumSpacing
    }
}

private fun LessonContentScope.theCore() {
    text("the_core_title") {
        text = "Fixing the core"
        style = TextStyle.Heading
    }
    image("the_core_img") {
        imageUrl = "https://i.ibb.co/3NXLNCr/the-core.webp"
    }
    text("the_core_msg") {
        text = textBuilder {
            line("Harry needs to recalibrate his spaceship's energy core, which requires a precise temperature setting.")
            line("This temperature is a constant value that Harry knows.")
            line("The core temperature is exactly $CoreTemp Kelvin.")
        }
        style = TextStyle.BodyMediumSpacing
    }
}

private fun LessonContentScope.questionCoreTempKelvin() {
    question("coreTempKelvinMath") {
        question = "What's the mathematical function for the core temperature?"
        clarification = "The core temperature should be ${CoreTemp}K"
        val xParam = "\"x\""
        answer(
            text = "coreTempK(x) = x + $CoreTemp",
            explanation = "The expected core temp should not depend on $xParam.",
        )
        answer(
            text = "coreTempK(x) = x",
            explanation = "The expected core temp should not depend on $xParam.",
        )
        answer(
            text = "coreTempK() = $CoreTemp",
            explanation = "That's right! The core temperature is constant.",
            correct = true,
        )
        answer(
            text = "coreTempK(x) = $CoreTemp",
            explanation = "$xParam is not used, therefore redundant.",
        )
    }
}

private fun LessonContentScope.questionCoreTempHaskell() {
    question("coreTempHaskellMath") {
        question = "What's the core temperature function in Haskell?"
        clarification = "Select the most explicit and appropriate function."
        answer(
            text = "coreTempK = $CoreTemp",
            explanation = "Quite right but we can explicitly specify the types."
        )
        answer(
            text = codeBuilder {
                line("coreTempK :: Double")
                line("coreTempK = $CoreTemp")
            },
            correct = true,
            explanation = "This is a constant function with explicitly defined types."
        )
        answer(
            text = codeBuilder {
                line("coreTempK :: Double -> Double")
                line("coreTempK x = $CoreTemp")
            },
            explanation = "The function should not accept a double parameter."
        )
        answer(
            text = codeBuilder {
                line("coreTempK :: Double -> Double")
                line("coreTempK x = x + $CoreTemp")
            },
            explanation = "The function should not depend on an input parameter."
        )
    }
}


fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}