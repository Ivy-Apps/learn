package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImage

object ProgrammingFundamentals : CourseDsl({
    name = "Programming Fundamentals"
    tagline = "Explore the core ideas powering today's software."
    imageUrl = "https://i.ibb.co/PTXn42F/fundamentals.webp"
    lesson(
        name = "Programming: Math in disguise",
        tagline = "Mathematical functions and formulas can do much more than you think.",
        imageUrl = LessonImage
    )
})