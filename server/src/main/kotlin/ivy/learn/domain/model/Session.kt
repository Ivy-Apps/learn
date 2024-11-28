package ivy.learn.domain.model

import kotlinx.datetime.Instant

data class Session(
    val token: SessionToken,
    val userId: UserId,
    val createdAt: Instant,
    val expiresAt: Instant,
)

@JvmInline
value class SessionToken(val value: String)