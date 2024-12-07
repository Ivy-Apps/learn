package ivy.learn.data.di

import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton
import ivy.learn.data.database.LearnDatabase
import ivy.learn.data.repository.*
import ivy.learn.data.repository.auth.SessionRepository
import ivy.learn.data.repository.auth.UserRepository
import ivy.learn.data.source.LessonContentDataSource

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        autoWireSingleton(::LearnDatabase)
        autoWire(::LessonContentDataSource)
        autoWire(::LessonsRepository)
        autoWire(::LessonProgressRepository)
        autoWire(::CoursesRepository)
        autoWire(::TopicsRepository)
        autoWire(::SessionRepository)
        autoWire(::UserRepository)
        autoWire(::AnalyticsRepository)
    }
}