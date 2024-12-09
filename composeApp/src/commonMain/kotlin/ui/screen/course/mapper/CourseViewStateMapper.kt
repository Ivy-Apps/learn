package ui.screen.course.mapper

import ivy.data.source.model.CourseResponse
import ivy.model.Lesson
import ivy.model.LessonId
import kotlinx.collections.immutable.toImmutableList
import ui.screen.course.CourseItemViewState
import ui.screen.course.CourseViewState
import ui.screen.course.ProgressViewState

class CourseViewStateMapper {
    fun CourseResponse.toViewState(): CourseViewState {
        val lessonsMap = lessons.associateBy(Lesson::id)
        val firstNotCompletedLesson = lessons.firstNotNullOfOrNull { lesson ->
            lesson.id.takeIf { !lesson.completed }
        }
        return CourseViewState(
            name = course.name,
            items = course.lessons.flatMap { lessonId ->
                lessonsMap[lessonId]
                    ?.toViewState(firstNotCompleted = firstNotCompletedLesson)
                    ?.let { lesson ->
                        listOf(
                            lesson,
                            CourseItemViewState.Arrow(lesson.progress)
                        )
                    }.orEmpty()
            }.dropLast(1).toImmutableList()
        )
    }

    private fun Lesson.toViewState(
        firstNotCompleted: LessonId?,
    ): CourseItemViewState.Lesson {
        return CourseItemViewState.Lesson(
            id = id.value,
            imageUrl = image.url,
            name = name,
            tagline = tagline,
            progress = when {
                completed -> ProgressViewState.Completed
                id == firstNotCompleted -> ProgressViewState.Current
                else -> ProgressViewState.Upcoming
            },
        )
    }
}