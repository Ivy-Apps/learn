package ui.screen.home.mapper

import ivy.data.source.model.TopicsResponse
import ivy.model.Course
import ivy.model.CourseId
import ivy.model.LessonId
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ui.screen.home.HomeItemViewState

class HomeViewStateMapper {
    fun TopicsResponse.toViewState(): ImmutableList<HomeItemViewState> {
        val coursesMap = courses.associateBy(Course::id)
        return topics.flatMap { topic ->
            listOf(
                HomeItemViewState.Section(title = topic.name)
            ) + topic.courses
                .mapNotNull { courseId ->
                    coursesMap[courseId]?.let { course ->
                        HomeItemViewState.Course(
                            id = course.id.value,
                            imageUrl = course.image.url,
                            name = course.name,
                            tagline = course.tagline,
                            progress = computeCourseProgress(
                                progress = progress,
                                course = course,
                            )
                        )
                    }
                }
        }.toImmutableList()
    }

    private fun computeCourseProgress(
        progress: Map<CourseId, Set<LessonId>>,
        course: Course,
    ): Float {
        val lessonsSet = course.lessons.toSet()
        val completedLessonsCount = progress[course.id].orEmpty()
            .filter { it in lessonsSet }
            .size
        return completedLessonsCount.toFloat() / lessonsSet.size
    }
}