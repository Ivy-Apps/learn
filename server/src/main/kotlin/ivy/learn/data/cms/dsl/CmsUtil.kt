package ivy.learn.data.cms.dsl

import ivy.model.LessonContent
import ivy.model.LessonItemId

val CourseImage =
    "https://i.ibb.co/nMLdcD5/DALL-E-2024-05-04-20-50-03-A-wide-banner-image-for-a-course-titled-Programming-Basics-with-the-tagli.webp"
val LessonImage = "https://i.ibb.co/7WY4N3R/lesson.webp"
val LessonImage2 = "https://i.ibb.co/QMzRZR3/lesson2.webp"


val EmptyContent = LessonContent(
    rootItem = LessonItemId(""),
    items = emptyMap()
)

fun nameToId(name: String): String = name.replace(" ", "-").lowercase()