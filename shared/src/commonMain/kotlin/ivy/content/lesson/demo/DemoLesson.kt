package ivy.content.lesson.demo

import ivy.content.CourseImage
import ivy.content.LessonImage
import ivy.model.LessonItemId
import ivy.model.TextStyle
import ivy.model.dsl.lessonContent
import ivy.model.dsl.printLessonJson

fun demoLesson() = lessonContent {
    text("title") {
        text = "Demo Lesson"
        style = TextStyle.Heading
    }
    text("content") {
        text = "This is a demo lesson for testing purposes."
        style = TextStyle.Body
    }
    image("img1") {
        imageUrl = LessonImage
    }
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
    text("content2") {
        text = "Good job! You answered your first question correctly. Here's another question for you."
        style = TextStyle.Body
    }
    sound("complete-sound") {
        buttonText = "Complete Lesson sound"
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/complete-lesson.mp3"
    }
    sound("ups") {
        buttonText = "Ups sound"
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/ups.wav"
    }
    sound("success") {
        buttonText = "Success sound"
        soundUrl = "https://github.com/ILIYANGERMANOV/ivy-resources/raw/master/ivy-learn/sounds/success.mp3"
    }
    image("img2") {
        imageUrl = CourseImage
    }
    text("content3") {
        text = "Congratulations! You're on fire!"
        style = TextStyle.Body
    }
    text("content4") {
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".repeat(3)
        style = TextStyle.Body
    }
    lessonNavigation("next") {
        text = "Go to Question 1"
        onClick = LessonItemId("q1")
    }
    openQuestion("q2") {
        question = "What is the answer to the ultimate question of life, the universe, and everything?"
        correctAnswer = "42"
    }
    text("end") {
        text = "End of demo lesson."
        style = TextStyle.Body
    }
}

fun main() {
    printLessonJson(demoLesson())
}