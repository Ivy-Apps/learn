package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import ivy.learn.data.database.tables.Analytics
import ivy.learn.domain.model.AnalyticsEvent
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class AnalyticsRepository {
    suspend fun insert(
        events: Collection<AnalyticsEvent>
    ): Either<String, Collection<AnalyticsEvent>> = catch({
        transaction {
            for (event in events) {
                Analytics.insert {
                    it[Analytics.id] = event.id
                    it[userId] = event.userId.value
                    it[Analytics.event] = event.name
                    it[time] = event.time
                }
            }
        }
        Either.Right(events)
    }) { e ->
        Either.Left("Failed to inset ${events.joinToString { it.toString() }} because $e")
    }
}