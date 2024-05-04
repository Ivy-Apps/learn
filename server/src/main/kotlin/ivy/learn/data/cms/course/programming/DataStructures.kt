package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImage
import ivy.learn.data.cms.dsl.LessonImage

object DataStructures : CourseDsl({
    name = "Data Structures"
    tagline = "Lists, Sets, Maps, Stacks, Trees and more."
    imageUrl = CourseImage
    lesson(
        name = "Arrays",
        tagline = "The simplest data structure.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Lists",
        tagline = "Arrays with superpowers. Dynamic sizing.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Linked Lists",
        tagline = "A different take on lists. Pointers and nodes.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Sets",
        tagline = "Unique elements. No duplicates.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Maps",
        tagline = "Key-Value pairs. Associative arrays.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Stacks",
        tagline = "LIFO: Last In, First Out.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Queues",
        tagline = "FIFO: First In, First Out.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Trees",
        tagline = "Hierarchical data structures.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Graphs",
        tagline = "Networks of nodes and edges.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Heaps",
        tagline = "Priority Queues. Efficiently find the max or min.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Tries",
        tagline = "Prefix Trees. Efficient string search.",
        imageUrl = LessonImage
    )
    lesson(
        name = "Hash Tables",
        tagline = "Fast lookups. Key-Value pairs.",
        imageUrl = LessonImage
    )
})