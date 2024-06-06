package ivy.content.lesson.programmingfundamentals

import ivy.content.LottieUrls
import ivy.model.TextStyle
import ivy.model.dsl.*

const val HarryPlanet = "KX-147"
const val CoreTemp = 314

fun programmingMathInDisguise() = lessonContent {
    intro()
    partCoreTempKelvin() // zero param function
    partKelvinToCelsius() // one param function
    partKelvinToCelsiusToFahrenheit() // one param function
    partFunctionComposition()
    summary()
}

private fun LessonContentScope.intro() {
    meetHarry()
    harryCrashed()
    helpHarry()
    fixTheCore()
}

private fun LessonContentScope.partCoreTempKelvin() {
    questionCoreTempKelvin()
    coreTempMathToHaskell()
    questionCoreTempHaskell()
}

private fun LessonContentScope.partKelvinToCelsius() {
    contentKelvinToCelsiusMath()
    questionKelvinToCelsiusMath()
    contentKelvinToCelsiusHaskell()
    questionKelvinToCelsiusHaskell()
}

private fun LessonContentScope.partKelvinToCelsiusToFahrenheit() {
    contentKelvinToCelsiusToFahrenheitMath()
    questionCelsiusToFahrenheitMath()
    contentKelvinToCelsiusToFahrenheitHaskell()
    questionCelsiusToFahrenheitHaskell()
}

private fun LessonContentScope.partFunctionComposition() {
    functionComposition()
    questionFunctionCompositionMath()
    functionCompositionOperator()
    questionFunctionCompositionOperatorMath()
    finalSolution()
    questionFinalSolutionHaskell()
}

private fun LessonContentScope.summary() {
    storyConclusion()
    lessonSummary()
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
    }
    text("harry_hi2") {
        text = textBuilder {
            line("He's also quite impatient and tends to test his ideas at Friday on production.")
        }
    }
}

private fun LessonContentScope.harryCrashed() {
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
        style = TextStyle.BodySpacingMedium
    }
}

private fun LessonContentScope.fixTheCore() {
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
        style = TextStyle.BodySpacingMedium
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

private fun LessonContentScope.coreTempMathToHaskell() {
    text("core_temp_transition") {
        text = textBuilder {
            line("Harry has successfully recalibrated the spaceship's energy core to $CoreTemp Kelvin.")
            line("But there's a catch! Harry's spaceship operates on an sophisticated Haskell-based system.")
            line("Haskell is renowned for its ability to create type-safe and reliable programs, which is crucial for Harry's mission.")
        }
        style = TextStyle.BodySpacingLarge
    }
    text("core_temp_transition2") {
        text = textBuilder {
            line("Why Haskell? Here are a few reasons:")
            bullet("Type Safety: Helps prevent errors before they cause problems.")
            bullet("Immutability: Ensures data remains unchanged, reducing unexpected behaviors.")
            bullet("Pure Functions: Guarantees the same output for the same input, every time.")
        }
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

private fun LessonContentScope.contentKelvinToCelsiusMath() {
    text("core_temp_to_kelvin_conversion") {
        text = textBuilder {
            line("Now that Harry has defined the core temperature function in Haskell, he needs to convert this temperature to Celsius.")
            line("This will allow Harry to double-check the temperature with an Earth thermometer.")
            line("Let's help Harry by defining the mathematical function to convert Kelvin to Celsius.")
        }
        style = TextStyle.BodySpacingLarge
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

private fun LessonContentScope.contentKelvinToCelsiusHaskell() {
    text("kelvin_to_celsius_haskell") {
        text = textBuilder {
            line("Harry needs to convert the Celsius conversion formula from math to Haskell to use it in his spaceship's system.")
        }
        style = TextStyle.BodySpacingLarge
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

private fun LessonContentScope.contentKelvinToCelsiusToFahrenheitMath() {
    text("contentKelvinToCelsiusToFahrenheitMath") {
        text = textBuilder {
            line("Harry needs to kick-start the spaceship's core by setting it to exactly the right temperature.")
            line("He has already converted the temperature from Kelvin to Celsius. However...")
        }
        style = TextStyle.BodySpacingLarge
    }
    text("contentKelvinToCelsiusToFahrenheitMath2") {
        text = textBuilder {
            line("He landed in a quirky town where the only available heater device is an old laptop running Android Studio, which only displays temperatures in Fahrenheit.")
            line("Let's help Harry convert the Celsius temperature to Fahrenheit mathematically so he can use the laptop to heat the core.")
        }
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

private fun LessonContentScope.contentKelvinToCelsiusToFahrenheitHaskell() {
    text("contentKelvinToCelsiusToFahrenheitHaskell") {
        text = textBuilder {
            line("As always, Harry will also need this function in Haskell so he can automate the process.")
        }
        style = TextStyle.BodySpacingLarge
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

private fun LessonContentScope.functionComposition() {
    text("functionComposition_title") {
        text = "Chapter 2: Function Composition"
        style = TextStyle.Heading
    }
    image("functionComposition_img") {
        imageUrl = "https://i.ibb.co/yqwLrq1/func-comp.jpg"
    }
    text("functionComposition") {
        text = textBuilder {
            line("Great work! We now have all the parts to repair Harry's spaceship core.")
            line("Before we proceed, let's understand a crucial mathematical concept that will help us in this process: function composition.")
        }
        style = TextStyle.BodySpacingMedium
    }
    text("functionComposition2") {
        text = textBuilder {
            line("In math, if we have a function f: A -> B and another function g: B -> C, we can combine them to form a new function h: A -> C.")
            line("This means we can apply f first, and then apply g to the result of f. Let's explore this concept further.")
        }
        style = TextStyle.Body
    }
}

private fun LessonContentScope.questionFunctionCompositionMath() {
    question("questionFunctionCompositionMath") {
        question = "Which two functions can be composed such that g(f(x)) is valid?"
        clarification =
            "Remember, function composition g(f(x)) requires the return type of f(x) to match the input type of g(x)."
        answer(
            text = "f: R -> R, g: R -> R",
            explanation = "That's right! The return type of f(x) matches the input type of g(x).",
            correct = true,
        )
        answer(
            text = "f: N -> R, g: R -> N",
            explanation = "That's right! The return type of f(x) matches the input type of g(x).",
            correct = true,
        )
        answer(
            text = "f: R -> N, g: N -> R",
            explanation = "That's right! The return type of f(x) matches the input type of g(x).",
            correct = true,
        )
        answer(
            text = "f: R -> N, g: R -> R",
            explanation = "Incorrect. The return type of f(x) does not match the input type of g(x).",
        )
        answer(
            text = "f: N -> N, g: R -> N",
            explanation = "Incorrect. The return type of f(x) does not match the input type of g(x).",
        )
    }
}

private fun LessonContentScope.functionCompositionOperator() {
    text("function_composition_operator") {
        text = textBuilder {
            line("In mathematics, the function composition operator (.) is used to combine two or more functions.")
            line("This operator is read as 'after'. For example, the expression g . f means 'g after f'.")
            line("When you see h(g(f(x))), it means you first apply f to x, then apply g to the result, and finally apply h to that result.")
        }
        style = TextStyle.BodySpacingLarge
    }
    text("function_composition_operator2") {
        text = textBuilder {

            line("Here's a tip: When reading function compositions, read the functions from right to left. This helps to understand the order in which the functions are applied.")
            line("Now, let's see how this works in practice with the following question.")
        }
    }
}

private fun LessonContentScope.questionFunctionCompositionOperatorMath() {
    question("questionFunctionCompositionOperatorMath") {
        question = "Given three functions f: A -> B, g: B -> C, and h: C -> D, which composition is correct?"
        clarification = "Remember the '.' composition operator reads as \"after\"."
        answer(
            text = "h . g . f",
            explanation = "That's right! The composition h(g(f(x))) is written as h . g . f.",
            correct = true,
        )
        answer(
            text = "f . g . h",
            explanation = "Incorrect. The correct composition is h(g(f(x))), not f(g(h(x))).",
        )
        answer(
            text = "g . f . h",
            explanation = "Incorrect. The correct composition is h(g(f(x))), not g(f(h(x))).",
        )
        answer(
            text = "h . f . g",
            explanation = "Incorrect. The correct composition is h(g(f(x))), not h(f(g(x))).",
        )
    }
}

private fun LessonContentScope.finalSolution() {
    text("finalSolution_title") {
        text = "Chapter 3: Fitting things together"
        style = TextStyle.Heading
    }
    image("finalSolution_img") {
        imageUrl = "https://i.ibb.co/p4Fz2p1/wiring-core-together.webp"
    }
    text("final_solution") {
        text = textBuilder {
            line("Harry is almost ready to fix the spaceship's core!")
            line("Here's a summary of what we have so far:")
            bullet("coreTempK :: Double")
            bullet("kelvinToCelsius :: Double -> Double")
            bullet("celsiusToFahrenheit :: Double -> Double")
        }
    }
    text("final_solution2") {
        text = textBuilder {
            line("In the final question, we'll need to help Harry compose these functions together to power-up the core.")
        }
    }
}

private fun LessonContentScope.questionFinalSolutionHaskell() {
    question("questionFinalSolutionHaskell") {
        question = "What's the correct Haskell function composition to power-up the spaceship's core?"
        clarification = "Note: There are exactly 2 correct answers."
        answer(
            text = codeBuilder {
                line("coreFix :: Double")
                line("coreFix = celsiusToFahrenheit . kelvinToCelsius . coreTempK")
            },
            explanation = "That's right! This composition correctly applies the functions in sequence.",
            correct = true,
        )
        answer(
            text = codeBuilder {
                line("coreFix :: Double")
                line("coreFix = kelvinToCelsius . celsiusToFahrenheit . coreTempK")
            },
            explanation = "Incorrect. The order of function composition is wrong.",
        )
        answer(
            text = codeBuilder {
                line("coreFix :: Double")
                line("coreFix = celsiusToFahrenheit(kelvinToCelsius(coreTempK()))")
            },
            explanation = "Correct! This alternative notation also correctly composes the functions.",
            correct = true,
        )
        answer(
            text = codeBuilder {
                line("coreFix :: Double")
                line("coreFix = coreTempK . kelvinToCelsius . celsiusToFahrenheit")
            },
            explanation = "Incorrect. The functions are not composed in the correct order.",
        )
        answer(
            text = codeBuilder {
                line("coreFix :: Double")
                line("coreFix = celsiusToFahrenheit(coreTempK . kelvinToCelsius)")
            },
            explanation = "Incorrect. The functions need to be composed in the correct sequence.",
        )
    }
}

private fun LessonContentScope.storyConclusion() {
    text("story_conclusion_title") {
        text = "Chapter 4: Mission Accomplished!"
        style = TextStyle.Heading
    }
    image("story_conclusion_img") {
        imageUrl =
            "https://i.ibb.co/C2v3ZcB/DALL-E-2024-06-06-17-42-49-An-illustration-of-Harry-the-quirky-alien-celebrating-the-successful-repa.webp"
    }
    text("story_conclusion") {
        text = textBuilder {
            line("With your help, Harry has successfully repaired the spaceship's core!")
            line("The core is now running perfectly at the right temperature, thanks to the correct function compositions.")
            line("Harry is extremely grateful for your assistance and is excited to continue his journey on Earth.")
        }
        style = TextStyle.BodySpacingMedium
    }
    text("story_conclusion2") {
        text = textBuilder {
            line("In the next lesson, Harry will help us take our skills to the next level by building our own programming language.")
            line("Get ready for more exciting adventures with Harry as he shares his advanced knowledge and explores the wonders of Earth with us!")
        }
    }
}

private fun LessonContentScope.lessonSummary() {
    text("lesson_summary_title") {
        text = "Lesson Summary"
        style = TextStyle.Heading
    }
    text("lesson_summary") {
        text = textBuilder {
            line("In this lesson, we learned:")
            bullet("The 1:1 correspondence between mathematical functions and programming functions.")
            bullet("Function composition, allowing us to combine multiple functions into one.")
            bullet("The function composition operator (.), read as 'after', and its use in sequencing functions.")
        }
    }
}

fun main() {
    val lesson = programmingMathInDisguise()
    printLessonJson(lesson)
    println(story(lesson))
}