package ivy.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Topic(
    val id: TopicId,
    val name: String,
    val courses: List<CourseId>
)

@JvmInline
@Serializable
value class TopicId(val value: String)