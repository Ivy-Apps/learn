package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.LessonsProgress
import ivy.learn.domain.model.LessonProgress
import ivy.learn.domain.model.UserId
import ivy.model.CourseId
import ivy.model.LessonId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class LessonProgressRepository {
    fun insert(
        progress: LessonProgress
    ): Either<String, LessonProgress> = catch({
        transaction {
            LessonsProgress.insert {
                it[userId] = progress.userId.value
                it[courseId] = progress.courseId.value
                it[lessonId] = progress.lessonId.value
                it[state] = progress.state
            }
        }
        Either.Right(progress)
    }) { e ->
        Either.Left("Failed to insert lesson progress $progress because $e")
    }

    fun findBy(
        userId: UserId,
        courseId: CourseId,
        lessonId: LessonId
    ): Either<String, LessonProgress?> = catch({
        transaction {
            LessonsProgress.selectAll()
                .where {
                    (LessonsProgress.userId eq userId.value)
                        .and(LessonsProgress.courseId eq courseId.value)
                        .and(LessonsProgress.lessonId eq lessonId.value)
                }
                .limit(1)
                .map(::rowToLessonProgress)
                .singleOrNull()
        }.right()
    }) { e ->
        Either.Left(
            "Failed to find lesson progress for $userId, $courseId, $lessonId because $e"
        )
    }

    private fun rowToLessonProgress(row: ResultRow): LessonProgress {
        return LessonProgress(
            userId = UserId(row[LessonsProgress.userId].value),
            courseId = CourseId(row[LessonsProgress.lessonId]),
            lessonId = LessonId(row[LessonsProgress.lessonId]),
            state = row[LessonsProgress.state],
        )
    }
}