package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import ivy.learn.CourseId
import ivy.learn.Lesson
import ivy.learn.LessonId
import ivy.learn.data.cms.course.CoursesContent
import ivy.learn.data.source.LessonContentDataSource

class LessonsRepository(
    private val lessonContentDataSource: LessonContentDataSource
) {
    suspend fun fetchLesson(
        courseId: CourseId,
        lessonId: LessonId
    ): Either<String, Lesson> = either {
        val lesson = CoursesContent.lessonsMap[lessonId]
        ensureNotNull(lesson) { "No lesson found for id $lessonId" }
        val content = lessonContentDataSource.fetchLessonContent(courseId, lessonId).bind()
        lesson.copy(content = content)
    }

    suspend fun fetchPartialLessons(courseId: CourseId): Either<String, List<Lesson>> = either {
        ensureNotNull(CoursesContent.coursesMap[courseId]?.lessons?.map { lessonId ->
            CoursesContent.lessonsMap[lessonId] ?: raise("No lesson found for id $lessonId")
        }) { "No lessons found for course id $courseId" }
    }
}