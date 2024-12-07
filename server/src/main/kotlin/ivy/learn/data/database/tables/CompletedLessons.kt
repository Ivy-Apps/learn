package ivy.learn.data.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object CompletedLessons : UUIDTable() {
    val userId = reference(
        name = "user_id",
        refColumn = Users.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
    ).index()
    val courseId = varchar("course_id", length = 120).index()
    val lessonId = varchar("lesson_id", length = 120)
}