package ivy.learn.data.cms.dsl

import ivy.model.LessonContent
import ivy.model.LessonItemId

val CourseImageUrl =
    "https://i.ibb.co/nMLdcD5/DALL-E-2024-05-04-20-50-03-A-wide-banner-image-for-a-course-titled-Programming-Basics-with-the-tagli.webp"
val LessonImageUrl = "https://i.ibb.co/m9kB3cJ/Screenshot-2024-05-04-at-21-22-18.png"


val EmptyContent = LessonContent(
    rootItem = LessonItemId(""),
    items = emptyMap()
)

fun nameToId(name: String): String = name.replace(" ", "-").lowercase()