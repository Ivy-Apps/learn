package ivy.learn.domain.model

import java.util.*

data class User(
    val id: UserId,
    val email: String,
    val name: String?,
    val profilePicture: String?,
)

@JvmInline
value class UserId(val value: UUID)