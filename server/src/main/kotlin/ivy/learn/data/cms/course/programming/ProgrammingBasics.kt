package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImage
import ivy.learn.data.cms.dsl.LessonImage2

object ProgrammingBasics : CourseDsl({
    name = "Programming Basics"
    tagline = "Learn the basics of programming from a different perspective."
    imageUrl = "https://i.ibb.co/PgG6ZWh/programming-basics.webp"
    lesson(
        name = "What is programming?",
        tagline = "An introduction to programming. And why we need it?",
        imageUrl = "https://i.ibb.co/0CbSJNR/what-is-programming.webp"
    )
    lesson(
        name = "Computations",
        tagline = "The purpose of computers is to compute. What does that mean?",
        imageUrl = "https://i.ibb.co/fp0DJ6h/computations.webp"
    )
    lesson(
        name = "Variables",
        tagline = "Storing computations for later use and more.",
        imageUrl = "https://i.ibb.co/Rc0fLvn/variables.webp"
    )
    lesson(
        name = "Branching",
        tagline = "This or that? If, when, else, and other choices.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Loops",
        tagline = "Doing things over and over again. And again. And again.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Functions",
        tagline = "f: A -> B. Extracting computations into reusable units.",
        imageUrl = LessonImage2
    )
    lesson(
        name = "Classes",
        tagline = "Objects. Functions with shared state. And the heart of OOP.",
        imageUrl = LessonImage
    )
})