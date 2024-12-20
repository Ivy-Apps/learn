package ivy.learn.data.database.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CompletedLessonsTable : Table(name = "completed_lessons") {
    val userId = reference(
        name = "user_id",
        refColumn = UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
    ).index()
    val courseId = varchar("course_id", length = 120).index()
    val lessonId = varchar("lesson_id", length = 120)
    val time = timestamp("time")
    override val primaryKey = PrimaryKey(
        columns = arrayOf(userId, courseId, lessonId)
    )
}