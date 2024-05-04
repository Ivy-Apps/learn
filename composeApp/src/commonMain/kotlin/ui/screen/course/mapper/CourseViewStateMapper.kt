package ui.screen.course.mapper

import ivy.data.source.model.CourseResponse
import ivy.model.Lesson
import kotlinx.collections.immutable.toImmutableList
import ui.screen.course.CourseItemViewState
import ui.screen.course.CourseViewState
import ui.screen.course.ProgressViewState

class CourseViewStateMapper {
    fun CourseResponse.toViewState(): CourseViewState {
        val lessonsMap = lessons.associateBy(Lesson::id)
        return CourseViewState(
            name = course.name,
            items = course.lessons.flatMap { lessonId ->
                lessonsMap[lessonId]?.toViewState()?.let {
                    listOf(it, CourseItemViewState.Arrow(ProgressViewState.Upcoming))
                } ?: emptyList()
            }.dropLast(1).toImmutableList()
        )
    }

    private fun Lesson.toViewState(): CourseItemViewState.Lesson {
        return CourseItemViewState.Lesson(
            imageUrl = image.url,
            name = name,
            tagline = tagline,
            progress = ProgressViewState.Upcoming,
        )
    }
}