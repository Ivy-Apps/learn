package ivy.data.source.model

import ivy.model.Course
import ivy.model.CourseId
import ivy.model.LessonId
import ivy.model.Topic
import kotlinx.serialization.Serializable

@Serializable
data class TopicsResponse(
    val topics: List<Topic>,
    val courses: List<Course>,
    val progress: Map<CourseId, Set<LessonId>>,
)
