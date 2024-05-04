package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImageUrl
import ivy.learn.data.cms.dsl.LessonImageUrl

object ProgrammingFundamentals : CourseDsl({
    name = "Programming Fundamentals"
    tagline = "Explore the core ideas powering today's software."
    imageUrl = CourseImageUrl
    lesson(
        name = "Type Systems",
        tagline = "The foundation of modern programming languages.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Algebraic Data Types (ADTs)",
        tagline = "SUMs, PRODUCTs and the core of data modeling.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Pattern Matching",
        tagline = "The power of ADTs in action.",
        imageUrl = LessonImageUrl
    )
})