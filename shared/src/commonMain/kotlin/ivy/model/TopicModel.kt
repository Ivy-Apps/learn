package ivy.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val name: String,
    val courses: List<CourseId>
)