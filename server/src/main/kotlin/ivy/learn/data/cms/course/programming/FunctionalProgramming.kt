package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImageUrl
import ivy.learn.data.cms.dsl.LessonImageUrl

object FunctionalProgramming : CourseDsl({
    name = "Functional Programming (FP)"
    tagline = "Programming with mathematical functions. Pure, declarative and powerful."
    imageUrl = CourseImageUrl
    lesson(
        name = "What is a function?",
        tagline = "A mathematical relation between a set of inputs and a set of outputs.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Types of functions",
        tagline = "Partial, total, pure. Learn these important distinctions.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Higher order functions",
        tagline = "Functions that take functions as arguments or return functions.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Recursion",
        tagline = "Functions that call themselves. The power of self-reference.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Immutability",
        tagline = "Values that never change. The key to functional programming.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Functor",
        tagline = "A type that can be mapped over. A container for a value.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Monad",
        tagline = "A type that can be flatMapped over. The heart of FP.",
        imageUrl = LessonImageUrl
    )
})