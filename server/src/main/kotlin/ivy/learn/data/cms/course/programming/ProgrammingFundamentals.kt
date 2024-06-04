package ivy.learn.data.cms.course.programming

import ivy.content.LessonImage
import ivy.learn.data.cms.dsl.CourseDsl

object ProgrammingFundamentals : CourseDsl({
    name = "Programming Fundamentals"
    tagline = "Explore the core ideas powering today's software."
    imageUrl = "https://i.ibb.co/PTXn42F/fundamentals.webp"
    lesson(
        name = "Programming: Math in disguise",
        tagline = "Mathematical functions and your calculator can do much more than you think." +
                " Goal: learn to create and compose pure math functions.",
        imageUrl = LessonImage
    )
})