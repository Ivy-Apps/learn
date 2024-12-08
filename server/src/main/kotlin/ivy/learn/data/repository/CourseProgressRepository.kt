package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.CompletedLessons
import ivy.learn.domain.model.CompletedLesson
import ivy.learn.domain.model.UserId
import ivy.model.CourseId
import ivy.model.LessonId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CourseProgressRepository {
    fun insert(
        completedLesson: CompletedLesson
    ): Either<String, CompletedLesson> = catch({
        transaction {
            CompletedLessons.insert {
                it[userId] = completedLesson.userId.value
                it[courseId] = completedLesson.courseId.value
                it[lessonId] = completedLesson.lessonId.value
                it[time] = completedLesson.time
            }
        }
        completedLesson.right()
    }) { e ->
        "Failed to insert $completedLesson because $e".left()
    }

    fun findById(
        userId: UserId,
        courses: List<CourseId>
    ): Either<String, List<CompletedLesson>> = catch({
        transaction {
            CompletedLessons.selectAll()
                .where {
                    (CompletedLessons.userId eq userId.value)
                        .and(CompletedLessons.courseId inList courses.map(CourseId::value))
                }
                .map(::rowToCompletedLesson)
        }.right()
    }) { e ->
        "Failed to find completed lessons by $courses because $e".left()
    }

    private fun rowToCompletedLesson(row: ResultRow): CompletedLesson {
        return CompletedLesson(
            userId = UserId(row[CompletedLessons.userId].value),
            courseId = CourseId(row[CompletedLessons.courseId]),
            lessonId = LessonId(row[CompletedLessons.lessonId]),
            time = row[CompletedLessons.time],
        )
    }
}