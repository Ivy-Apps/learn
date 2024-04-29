package ivy.learn.data.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.DiModule
import ivy.learn.data.database.DbConfigProvider
import ivy.learn.data.database.ExposedDatabase
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.data.source.LessonDataSource

object DataModule : DiModule {
    override fun init() = Di.appScope {
        register { DbConfigProvider() }
        register { ExposedDatabase(Di.get()) }
        register { LessonDataSource(Di.get()) }
        register { LessonsRepository(Di.get()) }
    }
}