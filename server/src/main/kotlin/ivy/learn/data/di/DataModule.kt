package ivy.learn.data.di

import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton
import ivy.learn.data.database.Database
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.data.repository.TopicsRepository
import ivy.learn.data.repository.auth.SessionRepository
import ivy.learn.data.source.LessonContentDataSource

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        autoWireSingleton(::Database)
        autoWire(::LessonContentDataSource)
        autoWire(::LessonsRepository)
        autoWire(::CoursesRepository)
        autoWire(::TopicsRepository)
        autoWire(::SessionRepository)
    }
}