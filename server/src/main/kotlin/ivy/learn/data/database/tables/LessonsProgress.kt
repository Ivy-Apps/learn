package ivy.learn.data.database.tables

import ivy.model.lesson.LessonProgressDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.json

object LessonsProgress : Table("lessons_progress") {
    val userId = reference(
        name = "user_id",
        refColumn = Users.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
    ).index()
    val courseId = varchar("course_id", length = 120).index()
    val lessonId = varchar("lesson_id", length = 120).index()
    val state = json<LessonProgressDto>(
        name = "state",
        serialize = { value ->
            Json.encodeToString(value)
        },
        deserialize = { json ->
            Json.decodeFromString(json)
        }
    )
    override val primaryKey = PrimaryKey(
        columns = arrayOf(userId, courseId, lessonId)
    )
}