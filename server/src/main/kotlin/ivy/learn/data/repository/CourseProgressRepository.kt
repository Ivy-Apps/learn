package ivy.learn.data.repository

import arrow.core.raise.catch
import ivy.learn.data.database.tables.CompletedLessons
import ivy.learn.domain.model.CompletedLesson
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class CourseProgressRepository {
    fun insert(
        completedLesson: CompletedLesson
    ) = catch({
        transaction {
            CompletedLessons.insert {
                it[userId] = completedLesson.userId.value
                it[courseId] = completedLesson.courseId.value
                it[lessonId] = completedLesson.lessonId.value
                it[time] = completedLesson.time
            }
        }
    }) { e ->
        "Failed to insert $completedLesson because $e"
    }
}