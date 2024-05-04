package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImage
import ivy.learn.data.cms.dsl.LessonImage2

object ProgrammingFundamentals : CourseDsl({
    name = "Programming Fundamentals"
    tagline = "Explore the core ideas powering today's software."
    imageUrl = "https://i.ibb.co/PTXn42F/fundamentals.webp"
    lesson(
        name = "Type Systems",
        tagline = "The foundation of modern programming languages.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Algebraic Data Types (ADTs)",
        tagline = "SUMs, PRODUCTs and the core of data modeling.",
        imageUrl = LessonImage2
    )
    lesson(
        name = "Pattern Matching",
        tagline = "The power of ADTs in action.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Unit Testing",
        tagline = "Ensuring your code works as expected.",
        imageUrl = LessonImage2
    )
})