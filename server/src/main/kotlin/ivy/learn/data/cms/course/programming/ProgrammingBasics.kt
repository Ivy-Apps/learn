package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImageUrl

object ProgrammingBasics : CourseDsl({
    name = "Programming Basics"
    tagline = "Learn the basics of programming from a different perspective."
    imageUrl =
        "https://i.ibb.co/nMLdcD5/DALL-E-2024-05-04-20-50-03-A-wide-banner-image-for-a-course-titled-Programming-Basics-with-the-tagli.webp"
    lesson(
        name = "What is programming?",
        tagline = "An introduction to programming. And why we need it?",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Computations",
        tagline = "The purpose of computers is to compute. What does that mean?",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Variables",
        tagline = "Storing computations for later use and more.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Branching",
        tagline = "This or that? If, when, else, and other choices.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Loops",
        tagline = "Doing things over and over again. And again. And again.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Functions",
        tagline = "f: A -> B. Extracting computations into reusable units.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Classes",
        tagline = "Objects. Functions with shared state. And the heart of OOP.",
        imageUrl = LessonImageUrl
    )
})