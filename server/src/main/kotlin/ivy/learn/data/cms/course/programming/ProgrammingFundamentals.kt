package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImage
import ivy.learn.data.cms.dsl.LessonImage

object ProgrammingFundamentals : CourseDsl({
    name = "Programming Fundamentals"
    tagline = "Explore the core ideas powering today's software."
    imageUrl = CourseImage
    lesson(
        name = "Type Systems",
        tagline = "The foundation of modern programming languages.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Algebraic Data Types (ADTs)",
        tagline = "SUMs, PRODUCTs and the core of data modeling.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Pattern Matching",
        tagline = "The power of ADTs in action.",
        imageUrl = LessonImage
    )
})