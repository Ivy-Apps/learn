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
    questionKelvinToCelsiusMath()
    questionKelvinToCelsiusHaskell()
    questionCelsiusToFahrenheitMath()
    questionCelsiusToFahrenheitHaskell()
    buildingFinalSolution()
    conclusion()
}

private fun LessonContentScope.meetHarry() {
    text("meet_harry") {
        text = "Meet Harry!"
        style = TextStyle.Heading
    }
    lottie("harry_hi_anim") {
        jsonUrl = LottieUrls.HarrySunglasses
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
            line("This temperature is a constant value that only Harry knows.")
            line("The core temperature is exactly $CoreTemp Kelvin.")
        }
        style = TextStyle.BodyMediumSpacing
    }
}

private fun LessonContentScope.questionCoreTempKelvin() {
    question("coreTempKelvinMath") {
        question = "What's the mathematical function for the core temperature?"
        clarification = "The core temperature should be ${CoreTemp}K."
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

private fun LessonContentScope.questionKelvinToCelsiusMath() {
    question("kelvinToCelsiusMath") {
        question = "What's the mathematical function to convert Kelvin to Celsius?"
        clarification = "1°C = 274.15K (tip: do the math!)"
        answer(
            text = "kelvinToCelsius(k) = k + 273.15",
            explanation = "kelvinToCelsius(274.15) = 274.16 + 273.15 = 547.31°C. Incorrect.",
        )
        answer(
            text = "kelvinToCelsius(k) = k - 274.15",
            explanation = "kelvinToCelsius(274.15) = 274.15 - 274.15 = 0°C. Incorrect.",
        )
        answer(
            text = "kelvinToCelsius(k) = k * 274.15",
            explanation = "kelvinToCelsius(10) = 10 * 274.15 = 2,741.5°C. Incorrect.",
        )
        answer(
            text = "kelvinToCelsius(k) = k - 273.15",
            explanation = "That's correct! kelvinToCelsius(274.15) = 274.4 - 273.15 = 1°C",
            correct = true,
        )
    }
}

private fun LessonContentScope.questionKelvinToCelsiusHaskell() {
    question("kelvinToCelsiusHaskell") {
        question = "What's the Haskell function to convert Kelvin to Celsius?"
        clarification = "The formula is: kelvinToCelsius(k) = k - 273.15."
        answer(
            text = codeBuilder {
                line("kelvinToCelsius :: Double -> Double")
                line("kelvinToCelsius k = k - 273.15")
            },
            explanation = "The function accepts a Double parameter named \"k\" and returns Double",
            correct = true,
        )
        answer(
            text = codeBuilder {
                line("kelvinToCelsius :: Double")
                line("kelvinToCelsius k = k - 273.15")
            },
            explanation = "Compilation error. This function should accept a Double parameter.",
        )
        answer(
            text = codeBuilder {
                line("kelvinToCelsius :: Double -> Double")
                line("kelvinToCelsius = k - 273.15")
            },
            explanation = "Compilation error. Variable not in scope: k",
        )
        answer(
            text = codeBuilder {
                line("kelvinToCelsius k = k - 273.15")
            },
            explanation = "It compiles but let's specify types explicitly.",
        )
    }
}

private fun LessonContentScope.questionCelsiusToFahrenheitMath() {
    question("celsiusToFahrenheitMath") {
        question = "What's the mathematical function to convert Celsius to Fahrenheit?"
        clarification = "Harry found that: 1°C = (1°C × 9/5) + 32 = 33.8°F"
        answer(
            text = "celsiusToFahrenheit(c) = (9/5) + 32",
            explanation = "Nope. E.g. celsiusToFahrenheit(1) = celsiusToFahrenheit(100)",
        )
        answer(
            text = "celsiusToFahrenheit(c) = (c * 9/5) + 32",
            explanation = "That's correct! celsiusToFahrenheit(5) = (5 * 9/5) + 32 = 41°F",
            correct = true,
        )
        answer(
            text = "celsiusToFahrenheit(c, k) = (c * 9/5) + k",
            explanation = "What's \"k\"? The formula should not depend on k.",
        )
        answer(
            text = "celsiusToFahrenheit(c) = c + (9/5 * 32)",
            explanation = "That's not the correct formula.",
        )
    }
}

private fun LessonContentScope.questionCelsiusToFahrenheitHaskell() {
    question("celsiusToFahrenheitHaskell") {
        question = "What's the Haskell function to convert Celsius to Fahrenheit?"
        clarification = "Formula: celsiusToFahrenheit(c) = (c * 9/5) + 32"
        answer(
            text = codeBuilder {
                line("celsiusToFahrenheit :: Double -> Double")
                line("celsiusToFahrenheit c = (c * 9/5) + 32")
            },
            explanation = "Correct! The function accepts a Double parameter named \"c\" and returns Double",
            correct = true,
        )
        answer(
            text = codeBuilder {
                line("celsiusToFahrenheit :: Double -> Double -> Double")
                line("celsiusToFahrenheit c = (c * 9/5) + 32")
            },
            explanation = "Compilation error. The function accepts 2 Double params but only uses the first \"c\".",
        )
        answer(
            text = codeBuilder {
                line("celsiusToFahrenheit :: Double -> Double -> Double")
                line("celsiusToFahrenheit c k = (c * 9/5) + k")
            },
            explanation = "Compiles but the formula should not depend on k.",
        )
        answer(
            text = codeBuilder {
                line("celsiusToFahrenheit :: Double")
                line("celsiusToFahrenheit c = (c * 9/5) + 32")
            },
            explanation = "Compilation error. The function should accept a Double parameter.",
        )
    }
}

private fun LessonContentScope.buildingFinalSolution() {
    text("building_final_solution") {
        text = "Building the final solution"
        style = TextStyle.Heading
    }
    text("final_solution_text") {
        text = textBuilder {
            line("Harry has all the functions he needs to fix his spaceship.")
            line("He needs to compose them to get the final solution.")
            line("Harry needs to convert the core temperature from Kelvin to Celsius.")
            line("Then he needs to convert the Celsius temperature to Fahrenheit.")
            line("Finally, he needs to set the spaceship's core temperature.")
        }
        style = TextStyle.Body
    }
}

private fun LessonContentScope.conclusion() {
    text("summary") {
        text = "To Recap:"
        style = TextStyle.Heading
    }
    text("recap_text") {
        text = textBuilder {
            line("You've met your new green friend Harry.")
            line("You've learned to create simple mathematical functions.")
            line("You saw that there is 1:1 correspondence of those functions in Haskell.")
            line("You learned to compose functions. g(f(x)).")
            line("You learned the function composition operator: h(x) = f ◦ g.")
            line("You've helped Harry fix his spaceship.")
        }
        style = TextStyle.Body
    }
    lottie("harry_bye_anim") {
        jsonUrl = LottieUrls.SpaceshipFixed
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
}