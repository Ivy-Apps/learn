package ivy.learn.domain.model

import ivy.model.auth.SessionToken
import kotlinx.datetime.Instant

data class Session(
    val token: SessionToken,
    val userId: UserId,
    val createdAt: Instant,
    val expiresAt: Instant,
)