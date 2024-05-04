package ivy.learn.data.cms.course.programming

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImageUrl
import ivy.learn.data.cms.dsl.LessonImageUrl

object DataStructures : CourseDsl({
    name = "Data Structures"
    tagline = "Lists, Sets, Maps, Stacks, Trees and more."
    imageUrl = CourseImageUrl
    lesson(
        name = "Arrays",
        tagline = "The simplest data structure.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Lists",
        tagline = "Arrays with superpowers. Dynamic sizing.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Linked Lists",
        tagline = "A different take on lists. Pointers and nodes.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Sets",
        tagline = "Unique elements. No duplicates.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Maps",
        tagline = "Key-Value pairs. Associative arrays.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Stacks",
        tagline = "LIFO: Last In, First Out.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Queues",
        tagline = "FIFO: First In, First Out.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Trees",
        tagline = "Hierarchical data structures.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Graphs",
        tagline = "Networks of nodes and edges.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Heaps",
        tagline = "Priority Queues. Efficiently find the max or min.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Tries",
        tagline = "Prefix Trees. Efficient string search.",
        imageUrl = LessonImageUrl
    )
    lesson(
        name = "Hash Tables",
        tagline = "Fast lookups. Key-Value pairs.",
        imageUrl = LessonImageUrl
    )
})