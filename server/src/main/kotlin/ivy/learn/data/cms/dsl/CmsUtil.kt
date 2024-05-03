package ivy.learn.data.cms.dsl

import ivy.model.LessonContent
import ivy.model.LessonItemId

val EmptyContent = LessonContent(
    rootItem = LessonItemId(""),
    items = emptyMap()
)

fun nameToId(name: String): String = name.replace(" ", "-").lowercase()