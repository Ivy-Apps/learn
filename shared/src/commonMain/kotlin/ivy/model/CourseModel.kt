package ivy.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Course(
    val id: CourseId,
    val name: String,
    val tagline: String,
    val icon: ImageUrl?,
    val lessons: List<LessonId>,
)

@Serializable
@JvmInline
value class CourseId(val value: String)