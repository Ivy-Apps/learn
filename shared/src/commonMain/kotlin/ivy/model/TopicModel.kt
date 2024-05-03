package ivy.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Topic(
    override val id: TopicId,
    val name: String,
    val courses: List<CourseId>
) : Identifiable<TopicId>

@JvmInline
@Serializable
value class TopicId(override val value: String) : Id