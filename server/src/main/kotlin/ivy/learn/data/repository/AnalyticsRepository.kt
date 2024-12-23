package ivy.learn.data.repository

import arrow.core.Either
import arrow.core.raise.catch
import ivy.learn.data.database.tables.AnalyticsTable
import ivy.learn.domain.model.AnalyticsEvent
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class AnalyticsRepository {
    fun insert(
        events: Set<AnalyticsEvent>
    ): Either<String, Set<AnalyticsEvent>> = catch({
        transaction {
            for (event in events) {
                AnalyticsTable.insert {
                    it[AnalyticsTable.id] = event.id
                    it[userId] = event.userId.value
                    it[AnalyticsTable.event] = event.eventName
                    it[time] = event.time
                    it[params] = event.params
                }
            }
        }
        Either.Right(events)
    }) { e ->
        Either.Left("Failed to insert ${events.joinToString { it.toString() }} because $e")
    }
}