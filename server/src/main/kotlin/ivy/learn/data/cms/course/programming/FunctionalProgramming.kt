package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImage

object FunctionalProgramming : CourseDsl({
    name = "Functional Programming (FP)"
    tagline = "Programming with mathematical functions. Pure, declarative and powerful."
    imageUrl = "https://i.ibb.co/Rpfw32H/fp.webp"
    lesson(
        name = "What is a function?",
        tagline = "A mathematical relation between a set of inputs and a set of outputs.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Types of functions",
        tagline = "Partial, total, pure. Learn these important distinctions.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Higher order functions",
        tagline = "Functions that take functions as arguments or return functions.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Recursion",
        tagline = "Functions that call themselves. The power of self-reference.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Immutability",
        tagline = "Values that never change. The key to functional programming.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Functor",
        tagline = "A type that can be mapped over. A container for a value.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Monad",
        tagline = "A type that can be flatMapped over. The heart of FP.",
        imageUrl = LessonImage
    )
})