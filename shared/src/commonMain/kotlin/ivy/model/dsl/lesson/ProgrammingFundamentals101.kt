package ivy.model.dsl.lesson

import ivy.model.LessonId
import ivy.model.LessonItemId
import ivy.model.TextContentStyle
import ivy.model.dsl.lesson

fun programmingFundamentals101() = lesson(
    id = LessonId("programming-fundamentals-101"),
    name = "Programming Fundamentals 101",
    tagline = "Learn the basics of programming",
) {
    mystery("question-1-answer") {
        text("Show explanation")
        hiddenItemId(LessonItemId("question-1-explanation"))
    }
    textItem("question-1-explanation") {
        text("1 + 1 = 2")
        style(TextContentStyle.Body)
    }
    question("question-1") {
        questionText("What is 1 + 1?")
        answer("1", "1 + 1 = 2", false)
        answer("2", "1 + 1 = 2", true)
        answer("3", "1 + 1 = 2", false)
        answer("4", "1 + 1 = 2", false)
    }
}