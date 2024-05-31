package ivy.learn.data.cms.lesson.demo

import ivy.learn.data.cms.dsl.CourseImage
import ivy.learn.data.cms.dsl.LessonImage
import ivy.model.LessonItemId
import ivy.model.TextStyle
import ivy.model.dsl.lessonContent
import ivy.model.dsl.printLessonJson

fun demoLesson() = lessonContent {
    textItem("title") {
        text = "Demo Lesson"
        style = TextStyle.Heading
    }
    textItem("content") {
        text = "This is a demo lesson for testing purposes."
        style = TextStyle.Body
    }
    image("img1", LessonImage)
    question("q1") {
        question = "What is the answer to the ultimate question of life, the universe, and everything?"
        answer(
            text = "42",
            correct = true,
            explanation = "The answer to the ultimate question of life, the universe, and everything is 42."
        )
        answer(
            text = "24",
            correct = false,
            explanation = "24 is not the answer to the ultimate question of life, the universe, and everything."
        )
        answer(
            text = "0",
            correct = false,
            explanation = "0 is not the answer to the ultimate question of life, the universe, and everything."
        )
        answer(
            text = "1",
            correct = false,
            explanation = "1 is not the answer to the ultimate question of life, the universe, and everything."
        )
    }
    textItem("content2") {
        text = "Good job! You answered your first question correctly. Here's another question for you."
        style = TextStyle.Body
    }
    sound(
        id = "complete-sound",
        text = "Complete Lesson sound",
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/complete-lesson.mp3"
    )
    sound(
        id = "ups",
        text = "Ups sound",
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/ups.wav"
    )
    sound(
        id = "success",
        text = "Success sound",
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/success.mp3"
    )
    image("img2", CourseImage)
    textItem("content3") {
        text = "Congratulations! You're on fire!"
        style = TextStyle.Body
    }
    textItem("content4") {
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".repeat(3)
        style = TextStyle.Body
    }
    lessonNavigation(
        id = "next",
        text = "Go to Question 1",
        onClick = LessonItemId("q1")
    )
    openQuestion("q2") {
        question = "What is the answer to the ultimate question of life, the universe, and everything?"
        correctAnswer = "42"
    }
    textItem("end") {
        text = "End of demo lesson."
        style = TextStyle.Body
    }
}

fun main() {
    printLessonJson(demoLesson())
}