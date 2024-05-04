package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.LessonImage
import ivy.learn.data.cms.dsl.LessonImage2

object ObjectOrientedProgramming : CourseDsl({
    name = "Object-Oriented Programming (OOP)"
    tagline = "Model the world with objects and classes. The foundation of modern software."
    imageUrl = "https://i.ibb.co/M6t3WnY/oop.webp"
    lesson(
        name = "What is an object?",
        tagline = "A self-contained entity with state and behavior.",
        imageUrl = LessonImage
    )
    lesson(
        name = "What is a class?",
        tagline = "A blueprint for creating objects. The definition of a type.",
        imageUrl = LessonImage2
    )
    lesson(
        name = "Encapsulation",
        tagline = "Hiding the internal state of an object and requiring all interaction to be performed through an object's methods.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Inheritance",
        tagline = "Creating new classes from existing classes. The 'is-a' relationship.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Polymorphism",
        tagline = "The ability of an object to take on many forms. The 'same' method name but different behavior.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Abstraction",
        tagline = "Hiding the implementation details of an object and showing only the necessary features.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Interfaces",
        tagline = "A contract that defines the behavior of an object. The 'can-do' relationship.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Design Patterns",
        tagline = "Solutions to common problems in software design.",
        imageUrl = LessonImage
    )
    lesson(
        name = "SOLID Principles",
        tagline = "Guidelines for writing clean, maintainable and scalable code.",
        imageUrl = LessonImage
    )
})