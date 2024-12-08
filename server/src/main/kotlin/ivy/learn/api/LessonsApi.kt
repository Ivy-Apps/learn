package ivy.learn.api

import arrow.core.raise.ensureNotNull
import io.ktor.server.routing.*
import ivy.learn.api.common.Api
import ivy.learn.api.common.getEndpointAuthenticated
import ivy.learn.api.common.model.ServerError.BadRequest
import ivy.learn.api.common.postEndpointAuthenticated
import ivy.learn.domain.LessonService
import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.lesson.LessonProgressDto
import ivy.model.lesson.LessonResponse

class LessonsApi(
    private val lessonService: LessonService,
) : Api {
    override fun Routing.endpoints() {
        loadLesson()
        saveLessonProgress()
    }

    private fun Routing.loadLesson() {
        getEndpointAuthenticated<LessonResponse>("/lessons/{courseId}/{lessonId}") { params, sessionToken ->
            val courseId = params["courseId"]?.let(::CourseId)
            val lessonId = params["lessonId"]?.let(::LessonId)
            ensureNotNull(courseId) { BadRequest("Course id is missing!") }
            ensureNotNull(lessonId) { BadRequest("Lesson id is missing!") }
            lessonService.loadLesson(
                sessionToken = sessionToken,
                courseId = courseId,
                lessonId = lessonId
            ).bind()
        }
    }

    private fun Routing.saveLessonProgress() {
        postEndpointAuthenticated<LessonProgressDto, Unit>(
            "/lessons/{courseId}/{lessonId}/progress"
        ) { params, body, sessionToken ->
            val courseId = params["courseId"]?.let(::CourseId)
            val lessonId = params["lessonId"]?.let(::LessonId)
            ensureNotNull(courseId) { BadRequest("Course id is missing!") }
            ensureNotNull(lessonId) { BadRequest("Lesson id is missing!") }
            lessonService.saveLessonProgress(
                sessionToken = sessionToken,
                courseId = courseId,
                lessonId = lessonId,
                dto = body,
            ).bind()
        }
    }
}