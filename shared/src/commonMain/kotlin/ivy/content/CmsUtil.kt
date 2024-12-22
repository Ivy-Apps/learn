package ivy.content

import ivy.learn.LessonContent
import ivy.learn.LessonItemId

val CourseImage = "https://i.ibb.co/7WY4N3R/lesson.webp"
val LessonImage = "https://i.ibb.co/7WY4N3R/lesson.webp"
val LessonImage2 = "https://i.ibb.co/QMzRZR3/lesson2.webp"


val EmptyContent = LessonContent(
    rootItem = LessonItemId(""),
    items = emptyMap()
)

fun nameToId(name: String): String = name.replace(" ", "-").lowercase()