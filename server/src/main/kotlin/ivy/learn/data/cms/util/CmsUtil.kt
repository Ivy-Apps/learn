package ivy.learn.data.cms.util

import ivy.model.LessonContent
import ivy.model.LessonItemId

val EmptyContent = LessonContent(
    rootItem = LessonItemId(""),
    items = emptyMap()
)