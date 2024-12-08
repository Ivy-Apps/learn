package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import ivy.learn.data.database.tables.LessonsProgress
import ivy.learn.domain.model.LessonProgress
import ivy.learn.domain.model.UserId
import ivy.model.CourseId
import ivy.model.LessonId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LessonProgressRepository {
    fun upsert(
        progress: LessonProgress
    ): Either<String, LessonProgress> = catch({
        transaction {
            if (rowExists(progress)) {
                update(progress)
            } else {
                insert(progress)
            }
        }
        Either.Right(progress)
    }) { e ->
        Either.Left("Failed to insert lesson progress $progress because $e")
    }

    private fun rowExists(progress: LessonProgress): Boolean {
        val row = LessonsProgress.select(LessonsProgress.lessonId)
            .where {
                primaryKeyMatches(progress.userId, progress.courseId, progress.lessonId)
            }
            .limit(1)
            .singleOrNull()
        return row != null
    }

    private fun update(progress: LessonProgress) {
        val updatedRows = LessonsProgress.update(
            where = {
                primaryKeyMatches(progress.userId, progress.courseId, progress.lessonId)
            }
        ) {
            it[state] = progress.state
        }
        if (updatedRows != 1) {
            throw IllegalStateException(
                "Unexpected number of lesson-progress rows updated! Updated $updatedRows rows."
            )
        }
    }

    private fun insert(progress: LessonProgress) {
        LessonsProgress.insert {
            it[userId] = progress.userId.value
            it[courseId] = progress.courseId.value
            it[lessonId] = progress.lessonId.value
            it[state] = progress.state
        }
    }

    fun findBy(
        userId: UserId,
        courseId: CourseId,
        lessonId: LessonId
    ): Either<String, LessonProgress?> = catch({
        transaction {
            LessonsProgress.selectAll()
                .where {
                    primaryKeyMatches(userId, courseId, lessonId)
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

    private fun SqlExpressionBuilder.primaryKeyMatches(
        userId: UserId,
        courseId: CourseId,
        lessonId: LessonId
    ): Op<Boolean> = (LessonsProgress.userId eq userId.value)
        .and(LessonsProgress.courseId eq courseId.value)
        .and(LessonsProgress.lessonId eq lessonId.value)

    private fun rowToLessonProgress(row: ResultRow): LessonProgress {
        return LessonProgress(
            userId = UserId(row[LessonsProgress.userId].value),
            courseId = CourseId(row[LessonsProgress.lessonId]),
            lessonId = LessonId(row[LessonsProgress.lessonId]),
            state = row[LessonsProgress.state],
        )
    }
}