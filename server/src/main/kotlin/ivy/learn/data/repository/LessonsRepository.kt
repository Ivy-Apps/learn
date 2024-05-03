package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.data.source.LessonContentDataSource
import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId

class LessonsRepository(
    private val lessonContentDataSource: LessonContentDataSource
) {
    suspend fun fetchLesson(
        courseId: CourseId,
        lessonId: LessonId
    ): Either<String, Lesson> = either {
        val lesson = CoursesContent.lessonsMap[lessonId]
        ensureNotNull(lesson) { "No lesson found for id $lessonId" }
        val content = lessonContentDataSource.fetchLessonById(courseId, lessonId).bind()
        lesson.copy(content = content)
    }

    suspend fun fetchPartialLessons(courseId: CourseId): Either<String, List<Lesson>> = either {
        ensureNotNull(CoursesContent.coursesMap[courseId]?.lessons?.map { lessonId ->
            CoursesContent.lessonsMap[lessonId] ?: raise("No lesson found for id $lessonId")
        }) { "No lessons found for course id $courseId" }
    }
}